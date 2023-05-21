package Biosphere;

import Biosphere.Animals.Herbivores.*;
import Biosphere.Animals.Predators.*;
import Island.*;
import SimulatorProperties.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Biosphere implements Runnable {
    private static final Double SATIATE_INCREASE = 0.1;
    protected final Double MAX_FOOD_TO_SATIATE;
    protected final Integer MAX_MOVE_DISTANCE;
    protected Double currentSatiety;

    protected Animal(BiosphereTypes type, Integer maxCountInCell, Double maxFoodToSatiate, Double startWeight, Integer maxMultiplying, Integer maxMoveDistance) {
        super(type, maxCountInCell, startWeight, maxMultiplying);
        MAX_MOVE_DISTANCE = maxMoveDistance;
        MAX_FOOD_TO_SATIATE = maxFoodToSatiate;
        currentSatiety = MAX_FOOD_TO_SATIATE;
    }

    public void eat() {
        Island.Cell currentCell = Island.getCell(getCurrentPosition());
        Optional<Animal> optionalAnimalVictim = findAnimalVictim();
        if (optionalAnimalVictim.isPresent()) {
            Animal animalVictim = optionalAnimalVictim.get();
            System.out.println("ANIMAL VICTIM " + animalVictim.getType().getUnicode());
            if (canEat(animalVictim.getType())) {
                System.out.println("ANIMAL CAN EAT ANIMAL");
                System.out.println("currentSatiety " + currentSatiety);
                currentSatiety += animalVictim.getWeight();
                System.out.println("new currentSatiety " + currentSatiety);
                currentSatiety = (currentSatiety > MAX_FOOD_TO_SATIATE) ? MAX_FOOD_TO_SATIATE : currentSatiety;
                System.out.println("final currentSatiety " + currentSatiety);
                Integer victimAmount = currentCell.getBiospheresAmountByType(animalVictim.getType());
                currentCell.updateBiospheresAmountByType(animalVictim.getType(), --victimAmount);
            }
        }
    }

    private Optional<Animal> findAnimalVictim() {
        List<Animal> animals = getCurrentCell().getAnimals();
        if (!animals.isEmpty()) {
            Integer randomAnimalIndex;
            do {
                randomAnimalIndex = ThreadLocalRandom.current().nextInt(animals.size());
                System.out.println("VICTIM --- " + animals.get(randomAnimalIndex).hashCode());
                System.out.println("PREDATOR --- " + this.hashCode());
            } while (animals.get(randomAnimalIndex).equals(this) && !(animals.contains(this) && animals.size() == 1));
            return Optional.ofNullable(animals.remove(randomAnimalIndex.intValue()));
        } else {
            return Optional.empty() ;
        }
    }

    private void decreaseSatiety() {
        currentSatiety -= MAX_FOOD_TO_SATIATE * SATIATE_INCREASE;
    }

    private void multiply() {
        Integer multiplyCandidates = getCurrentCell().getBiospheresAmountByType(TYPE);
        if (multiplyCandidates > 1) {
            try {
                getCurrentCell().getAnimals().add(Animal.createAnimal(TYPE).orElseThrow());
                getCurrentCell().getBiospheresByType().replace(TYPE, multiplyCandidates, ++multiplyCandidates);
            } catch (NoSuchElementException exception) {
                System.out.println("Can't multiply");
            }
        }
    }

    public void move() {
        System.out.println(this + " MOVE");
        Island.Cell currentCell = getCurrentCell();
        System.out.println("CURRENT POSITION " + currentCell);
        Direction directionMove = chooseDirectionMove();
        System.out.println("DIRECTION MOVE " + directionMove);
        Integer directionDistance = ThreadLocalRandom.current().nextInt(1, MAX_MOVE_DISTANCE + 1);
        System.out.println("DIRECTION DISTANCE " + directionDistance);
            Island.Cell newCell = switch (directionMove) {
                case UP -> {
                    Integer newCoordinate = currentPosition.getY() - directionDistance;
                    while (newCoordinate < 0) {
                        newCoordinate = currentPosition.getY() - (--directionDistance);
                    }
                    yield new Island.Cell(currentPosition.getX(), newCoordinate);
                }
                case DOWN -> {
                    Integer newCoordinate = currentPosition.getY() + directionDistance;
                    while (newCoordinate >= SimulationProperties.ISLAND_HEIGHT) {
                        newCoordinate = currentPosition.getY() + (--directionDistance);
                    }
                    yield new Island.Cell(currentPosition.getX(), newCoordinate);
                }
                case LEFT -> {
                    Integer newCoordinate = currentPosition.getX() - directionDistance;
                    while (newCoordinate < 0) {
                        newCoordinate = currentPosition.getX() - (--directionDistance);
                    }
                    yield new Island.Cell(newCoordinate, currentPosition.getY());
                }
                case RIGHT -> {
                    Integer newCoordinate = currentPosition.getX() + directionDistance;
                    while (newCoordinate >= SimulationProperties.ISLAND_WIDTH) {
                        newCoordinate = currentPosition.getX() + (--directionDistance);
                    }
                    yield new Island.Cell(newCoordinate, currentPosition.getY());
                }
                case STAY -> currentCell;
            };
        swapCells(currentCell, newCell);
    }

    private void swapCells(Island.Cell from, Island.Cell to) {
        removeFromCell(from);

        Island.Cell.Position endPosition = to.getPosition();
        Island.Cell endCell = Island.getCell(endPosition);
        Integer amountThisTypeInEndCell;
        if (endCell.getBiospheresAmountByType(getType()) != null) {
            amountThisTypeInEndCell = endCell.getBiospheresAmountByType(getType());
        } else {
            amountThisTypeInEndCell = 0;
        }
        endCell.updateBiospheresAmountByType(getType(), ++amountThisTypeInEndCell);
        endCell.getAnimals().add(this);
        System.out.println("NEW CELL " + endCell);
        System.out.println("END POS " + endCell.getPosition());
    }

    private void removeFromCell(Island.Cell from) {
        Integer currentAmountInStartCell = from.getBiospheresAmountByType(getType());
        from.updateBiospheresAmountByType(getType(), --currentAmountInStartCell);
        from.getAnimals().remove(this);
    }

    private Direction chooseDirectionMove() {
        Integer directionIndex = ThreadLocalRandom.current().nextInt(Direction.values().length);
        return switch (directionIndex) {
            case 1 -> Direction.DOWN;
            case 2-> Direction.UP;
            case 3 -> Direction.LEFT;
            case 4 -> Direction.RIGHT;
            default -> Direction.STAY;
        };
    }

    public void die() {
        if (currentSatiety > 0) {
            return;
        }
        System.out.println(this + " HAS BEEN DIED");
        removeFromCell(getCurrentCell());
    }

    //Проверка, может ли животное съесть другое животное, с учетом вероятности
    protected boolean canEat(BiosphereTypes victim) {
        Integer randomChance = ThreadLocalRandom.current().nextInt(0, 100);
        System.out.println("randomChance to eat " + randomChance);
        System.out.println("Chance to eat " + EatingProperties.getChanceToEat(getType(), victim));
        return EatingProperties.getChanceToEat(getType(), victim) > randomChance;
    }

    //Create any kind of Animal
    private static Optional<Animal> createAnimal(){
        Random random = new Random();
        Animal creatingAnimal = switch (random.nextInt(3)) {
            case 0 -> new Wolf();
            case 1 -> new Fox();
            case 2 -> new Sheep();
            default -> null;
        };
        return Optional.ofNullable(creatingAnimal);
    }

    private static Optional<Animal> createAnimal(BiosphereTypes parentsAnimalType){
        Animal creatingAnimal = switch (parentsAnimalType) {
            case WOLF -> new Wolf();
            case FOX -> new Fox();
            case SHEEP -> new Sheep();
            default -> null;
        };
        return Optional.ofNullable(creatingAnimal);
    }

    private void animalBehaviorLogic() {
        if (currentSatiety.equals(MAX_FOOD_TO_SATIATE)) {
            Integer randomAction = ThreadLocalRandom.current().nextInt(0, 2);
            switch (randomAction) {
                case 1 -> {
                    move();
                    System.out.println("ANIMAL MOVE");
                }
                case 2 -> {
                    System.out.println("ANIMAL MULTIPLY");
                    multiply();
                    decreaseSatiety();
                }
            }
        } else if (currentSatiety <= 0){
            System.out.println("ANIMAL DIE");
            die();
        } else {
            System.out.println("ANIMAL EAT");
            eat();
        }
    }

    @Override
    public void run() {
        System.out.println("ANIMAL STEP");
        animalBehaviorLogic();
    }

    @Override
    public String toString() {
        return "Animal{ " + TYPE.getUnicode() +
                "\tPosition= " + "x:" + currentPosition.getX() + " y:" + currentPosition.getY() +
                "\tcurrentSatiety=" + currentSatiety +
                "\t, weight=" + weight +
                '}';
    }
}
