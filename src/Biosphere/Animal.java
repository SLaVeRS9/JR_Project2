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

    // Animal eats other animal method
    public void eat() {
        Island.Cell currentCell = Island.getCell(getCurrentPosition());
        Optional<Animal> optionalAnimalVictim = findAnimalVictim();
        if (optionalAnimalVictim.isPresent()) {
            Animal animalVictim = optionalAnimalVictim.get();
            if (canEat(animalVictim.getType())) {
                currentSatiety += animalVictim.getWeight();
                currentSatiety = (currentSatiety > MAX_FOOD_TO_SATIATE) ? MAX_FOOD_TO_SATIATE : currentSatiety;
                Integer victimAmount = currentCell.getBiospheresAmountByType(animalVictim.getType());
                currentCell.updateBiospheresAmountByType(animalVictim.getType(), --victimAmount);
                System.out.println("Animal " + this.getType().getUnicode() + " has eaten " + animalVictim.getType().getUnicode());
            }
        }
    }

    // Animal finds victim to eat method
    private Optional<Animal> findAnimalVictim() {
        List<Animal> animals = getCurrentCell().getAnimals();
        if (!animals.isEmpty()) {
            Integer randomAnimalIndex;
            do {
                randomAnimalIndex = ThreadLocalRandom.current().nextInt(animals.size());
            } while (animals.get(randomAnimalIndex).equals(this) && !(animals.contains(this) && animals.size() == 1));
            return Optional.ofNullable(animals.remove(randomAnimalIndex.intValue()));
        } else {
            return Optional.empty() ;
        }
    }

    // Animal starvation logic
    private void decreaseSatiety() {
        currentSatiety -= MAX_FOOD_TO_SATIATE * SATIATE_INCREASE;
    }

    // Animals multiply logic. Now realize only 1 child
    private void multiply() {
        Integer multiplyCandidates = getCurrentCell().getBiospheresAmountByType(TYPE);
        if (multiplyCandidates > 1) {
            try {
                getCurrentCell().getAnimals().add(Animal.createAnimal(TYPE).orElseThrow());
                getCurrentCell().getBiospheresByType().replace(TYPE, multiplyCandidates, ++multiplyCandidates);
                System.out.println("Animal has multiplied");
            } catch (NoSuchElementException exception) {
                System.out.println(exception.getMessage());
            }
        } else {
            System.out.println("Animal can't multiply");
        }
    }

    // Animal move logic
    public void move() {
        Island.Cell currentCell = getCurrentCell();
        Direction directionMove = chooseDirectionMove();
        Integer directionDistance = ThreadLocalRandom.current().nextInt(1, MAX_MOVE_DISTANCE + 1);
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
        System.out.println("Animal " + this.getType().getUnicode() + " has moved from " + currentCell + " "
                + directionMove + " " + directionDistance + " to " + newCell);
    }

    // The logic of moving an animal from cell to cell
    private void swapCells(Island.Cell from, Island.Cell to) {
        removeFromCell(from);
        Island.Cell.Position endPosition = to.getPosition();
        Island.Cell endCell = Island.getCell(endPosition);
        Integer amountThisTypeInEndCell = endCell.getBiospheresAmountByType(getType()) != null
                ? endCell.getBiospheresAmountByType(getType())
                : 0;
        endCell.updateBiospheresAmountByType(getType(), ++amountThisTypeInEndCell);
        endCell.getAnimals().add(this);
    }

    // The logic of removing an animal from cell
    private void removeFromCell(Island.Cell from) {
        Integer currentAmountInStartCell = from.getBiospheresAmountByType(getType());
        from.updateBiospheresAmountByType(getType(), --currentAmountInStartCell);
        from.getAnimals().remove(this);
    }

    // The logic of choosing the direction of movement of the animal
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

    // Animal die logic
    public void die() {
        if (currentSatiety > 0) {
            return;
        }
        System.out.println(this + " has died");
        removeFromCell(getCurrentCell());
    }

    // Checking if an animal can eat another animal
    protected boolean canEat(BiosphereTypes victim) {
        Integer randomChance = ThreadLocalRandom.current().nextInt(0, 100);
        return EatingProperties.getChanceToEat(getType(), victim) > randomChance;
    }

    // Create any kind of Animal
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

    // Create animal by type
    private static Optional<Animal> createAnimal(BiosphereTypes parentsAnimalType){
        Animal creatingAnimal = switch (parentsAnimalType) {
            case WOLF -> new Wolf();
            case FOX -> new Fox();
            case SHEEP -> new Sheep();
            default -> null;
        };
        return Optional.ofNullable(creatingAnimal);
    }

    // Animal behavior logic
    private void animalBehaviorLogic() {
        if (currentSatiety.equals(MAX_FOOD_TO_SATIATE)) {
            Integer randomAction = ThreadLocalRandom.current().nextInt(0, 2);
            switch (randomAction) {
                case 0 -> {
                    move();
                    decreaseSatiety();
                }
                case 1 -> {
                    multiply();
                    decreaseSatiety();
                }
            }
        } else if (currentSatiety <= 0){
            die();
        } else {
            Integer randomAction = ThreadLocalRandom.current().nextInt(0, 3);
            switch (randomAction) {
                case 0 -> {
                    move();
                    decreaseSatiety();
                }
                case 1 -> {
                    multiply();
                    decreaseSatiety();
                }
                case 2 -> eat();
            }
        }
    }

    @Override
    public void run() {
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
