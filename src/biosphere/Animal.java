package biosphere;

import biosphere.animals.herbivores.*;
import biosphere.animals.predators.*;
import island.*;
import simulatorProperties.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Biosphere implements Runnable {
    protected final Double MAX_FOOD_TO_SATIATE;
    protected final Integer MAX_MOVE_DISTANCE;
    protected Double currentSatiety;

    protected Animal(BiosphereTypes type, Integer maxCountInCell, Double maxFoodToSatiate, Double startWeight, Integer maxMultiplying, Integer maxMoveDistance) {
        super(type, maxCountInCell, startWeight, maxMultiplying);
        MAX_MOVE_DISTANCE = maxMoveDistance;
        MAX_FOOD_TO_SATIATE = maxFoodToSatiate;
        currentSatiety = MAX_FOOD_TO_SATIATE;
    }

    // Animal behavior logic
    private void animalBehaviorLogic() {
        if (currentSatiety.equals(MAX_FOOD_TO_SATIATE)) {
            Integer randomAction = ThreadLocalRandom.current().nextInt(0, 2);
            switch (randomAction) {
                case 0 -> {
                    System.out.println("Animal " + this + " in cell " + this.getCurrentCell().getPosition() + " move");
                    move();
                    decreaseSatiety();
                }
                case 1 -> {
                    System.out.println("Animal " + this + " in cell " + this.getCurrentCell().getPosition() + " multiply");
                    multiply();
                    decreaseSatiety();
                }
            }
        } else if (currentSatiety <= 0){
            System.out.println("Animal " + this + " in cell " + this.getCurrentCell().getPosition() + " die");
            die();
        } else {
            Integer randomAction = ThreadLocalRandom.current().nextInt(0, 3);
            switch (randomAction) {
                case 0 -> {
                    System.out.println("Animal " + this + " in cell " + this.getCurrentCell().getPosition() + " move");
                    move();
                    decreaseSatiety();
                }
                case 1 -> {
                    System.out.println("Animal " + this + " in cell " + this.getCurrentCell().getPosition() + " multiply");
                    multiply();
                    decreaseSatiety();
                }
                case 2 -> {
                    System.out.println("Animal " + this + " in cell " + this.getCurrentCell().getPosition() + " eat");
                    eat();
                }
            }
        }
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
                synchronized (currentCell.getBiospheresByType()) {
                    Integer victimAmount = currentCell.getBiospheresAmountByType(animalVictim.getType());
                    currentCell.updateBiospheresAmountByType(animalVictim.getType(), --victimAmount);
                }
                synchronized (currentCell.getAnimals()) {
                   currentCell.getAnimals().remove(animalVictim);
                }
                System.out.println("Animal " + this.getType().getUnicode() + " has eaten " + animalVictim.getType().getUnicode());
            } else {
                System.out.println("Animal " + this.getType().getUnicode() + " has not eaten " + animalVictim.getType().getUnicode());
            }
        }
    }

    // Animal finds victim to eat method
    private Optional<Animal> findAnimalVictim() {
        List<Animal> animals;
        synchronized (getCurrentCell().getAnimals()){
            animals = getCurrentCell().getAnimals();
        }
        if (!animals.isEmpty()) {
            Integer randomAnimalIndex = ThreadLocalRandom.current().nextInt(animals.size());
            while (animals.get(randomAnimalIndex).equals(this) || (animals.contains(this) && animals.size() == 1)) {
                randomAnimalIndex = ThreadLocalRandom.current().nextInt(animals.size());
                Animal animal = animals.get(randomAnimalIndex);
                Optional<Animal> optionalAnimal = Optional.ofNullable(animal);
                return optionalAnimal;
            }
            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    // Checking if an animal can eat another animal
    protected boolean canEat(BiosphereTypes victim) {
        Integer randomChance = ThreadLocalRandom.current().nextInt(0, 100);
        return EatingProperties.getChanceToEat(getType(), victim) > randomChance;
    }

    // Animal starvation logic
    private void decreaseSatiety() {
        currentSatiety -= MAX_FOOD_TO_SATIATE * SimulationProperties.HEALTH_PERCENT_DECREASE_FROM_HUNGER;
    }

    // Animals multiply logic. Now realize only 1 child
    private void multiply() {
        Integer multiplyCandidates;
        synchronized (getCurrentCell().getBiospheresByType()){
            multiplyCandidates = getCurrentCell().getBiospheresAmountByType(TYPE);
        }

        if (multiplyCandidates > 1 && multiplyCandidates < getMaxMultiplying()) {
            Optional<Animal> optionalAnimal = Animal.createAnimal(getType());
            if (optionalAnimal.isEmpty()) {
                return;
            }
            Animal animal = optionalAnimal.get();
            animal.setPosition(currentPosition);
            synchronized (getCurrentCell().getAnimals()) {
                getCurrentCell().getAnimals().add(animal);
            }
            synchronized (getCurrentCell().getBiospheresByType()) {
                getCurrentCell().getBiospheresByType().replace(TYPE, multiplyCandidates, ++multiplyCandidates);
            }
            System.out.println("Animal " + this + " has multiplied");
        } else {
            System.out.println("Animal " + this + " can't multiply");
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
            synchronized (Island.getCell(newCell.getPosition()).getBiospheresByType()) {
                Island.Cell cell = Island.getCell(newCell.getPosition());
                System.out.println(cell.getBiospheresByType());
                System.out.println(cell.getBiospheresAmountByType(getType()));
                Integer biospheresAmountByType = cell.getBiospheresAmountByType(getType()) != null
                        ? cell.getBiospheresAmountByType(getType())
                        : 0;
                if(biospheresAmountByType >= getMaxCountInCell()){
                    return;
                }
            }
        System.out.println("Animal " + this.getType().getUnicode() + " has moved from " + currentCell.getPosition() + " "
                + directionMove.toString().toLowerCase() + " " + directionDistance + " to " + newCell.getPosition());
        swapCells(currentCell, newCell);
        currentPosition = newCell.getPosition();
    }

    // The logic of choosing the direction of movement of the animal
    private Direction chooseDirectionMove() {
        Integer directionIndex = ThreadLocalRandom.current().nextInt(Direction.values().length - 1);
        return switch (directionIndex) {
            case 0 -> Direction.DOWN;
            case 1-> Direction.UP;
            case 2 -> Direction.LEFT;
            case 3 -> Direction.RIGHT;
            default -> Direction.STAY;
        };
    }

    // The logic of moving an animal from cell to cell
    private void swapCells(Island.Cell from, Island.Cell to) {
        removeFromCell(from);
        Island.Cell.Position endPosition = to.getPosition();
        Island.Cell endCell = Island.getCell(endPosition);
        synchronized (endCell.getBiospheresByType()) {
            Integer amountThisTypeInEndCell = endCell.getBiospheresAmountByType(getType()) != null
                    ? endCell.getBiospheresAmountByType(getType())
                    : 0;
            endCell.updateBiospheresAmountByType(getType(), ++amountThisTypeInEndCell);
        }
        synchronized (endCell.getAnimals()) {
            endCell.getAnimals().add(this);
        }
    }

    // The logic of removing an animal from cell
    private void removeFromCell(Island.Cell from) {
        synchronized (from.getBiospheresByType()){
            Integer currentAmountInStartCell = from.getBiospheresAmountByType(getType());
            from.updateBiospheresAmountByType(getType(), --currentAmountInStartCell);
        }
        synchronized (from.getAnimals()) {
            from.getAnimals().remove(this);
        }
    }

    // Animal die logic
    public void die() {
        if(getCurrentPosition() == null) {
            return;
        }
        if (currentSatiety > 0) {
            return;
        }
        System.out.println(this + " has been died");
        synchronized (this) {
            removeFromCell(getCurrentCell());
        }
        currentPosition = null;
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
    public static Optional<Animal> createAnimal(BiosphereTypes parentsAnimalType){
        Animal creatingAnimal = switch (parentsAnimalType) {
            case WOLF -> new Wolf();
            case FOX -> new Fox();
            case SHEEP -> new Sheep();
            default -> null;
        };
        return Optional.ofNullable(creatingAnimal);
    }

    @Override
    public void run() {
        System.out.println("Start animal logic" + this);
        animalBehaviorLogic();
    }

    @Override
    public String toString() {
        return "{ " + TYPE.getUnicode() +
                "\tPosition= " +
                "x:" + (currentPosition != null ? currentPosition.getX() : " no X pos") +
                " y:" + (currentPosition != null ? currentPosition.getY() : " no Y pos") +
                "\tsatiety=" + ((int)(currentSatiety * 100)) / 100.0  +
                "\t" + weight +
                "kg" +
                '}';
    }
}
