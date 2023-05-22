package Island;

import Biosphere.*;
import Simulation.Simulation;
import SimulatorProperties.SimulationProperties;
import ThreadPools.Test;
import java.util.*;
import java.util.concurrent.*;
import java.util.random.RandomGenerator;

public class Island implements Runnable {
    private static final List<List<Cell>> islandMap = new ArrayList<>(SimulationProperties.ISLAND_WIDTH);
    private static final Integer CHANCE_TO_SET_ANIMAL = 30;
    private static final List<List<Cell>> unmodifiableIslandMap = Collections.unmodifiableList(islandMap);
    private static final Island ISLAND = new Island();
    public static List<List<Cell>> getIslandMap() {
        return unmodifiableIslandMap;
    }
    private static boolean simulationIsRunning = true;
    public static void stopSimulation() {
        simulationIsRunning = false;
    }
    public static boolean getSimulationIsRunning(){
        return simulationIsRunning;
    }
    private static Integer day = 1;
    private Island(){}

    public static Island getIsland() {
        return ISLAND;
    }

    //Generate island map to use
    public static void generateMap(){
        createMap();
        System.out.println("\nDay " + day + "\nThe situation on the island at the beginning\n");

        //Add plants to the map
        addPlants();
        
        //Add animals to the map
        addAnimals();

        //Print map
        System.out.println("\nCurrent statistics");
        printMap();
    }

    public static Cell getCell(Integer x, Integer y) {
        return islandMap.get(x).get(y);
    }

    public static Cell getCell(Cell.Position position) {
        return getCell(position.getX(), position.getY());
    }

    public static Cell getRandomCell() {
        Integer randomX = ThreadLocalRandom.current().nextInt(SimulationProperties.ISLAND_WIDTH);
        Integer randomY = ThreadLocalRandom.current().nextInt(SimulationProperties.ISLAND_HEIGHT);
        return getCell(randomX, randomY);
    }

    public static Integer getDay(){
        return day;
    }

    // Create map
    private static void createMap() {
        //Create island map
        for (int i = 0; i < SimulationProperties.ISLAND_WIDTH; i++) {
            List<Cell> islandMapVertical = new ArrayList<>();
            for (int j = 0; j < SimulationProperties.ISLAND_HEIGHT; j++) {
                Cell newCell = new Cell(i, j);
                islandMapVertical.add(newCell);
            }
            islandMap.add(Collections.unmodifiableList(islandMapVertical));
        }
    }

    // Add plants on all island map
    private static void addPlants(){
        for (List<Cell> cells : islandMap) {
            for (Cell cell : cells) {
                Plant plant = Plant.getRandomPlant();
                cell.addBiosphere(plant);
            }
        }
    }

    // Add animals on all island map
    private static void addAnimals() {
        List<Animal> animals = SimulationProperties.startAnimalsPopulation;
        while (animals.size() > 0) {
            for (List<Cell> cells : islandMap) {
                for (Cell cell : cells) {
                    int random = RandomGenerator.getDefault().nextInt(100);
                    if (random < CHANCE_TO_SET_ANIMAL) {
                        if (animals.size() == 0) {
                            break;
                        }
                        Animal animal = animals.remove(animals.size() - 1);
                        animal.setPosition(cell.position);
                        Integer currentAnimalsByTypeAmount = cell.amountBiospheresByType.get(animal.getType());
                        if (currentAnimalsByTypeAmount == null) {
                            cell.amountBiospheresByType.put(animal.getType(), 1);
                        } else {
                            cell.amountBiospheresByType.put(animal.getType(), ++currentAnimalsByTypeAmount);
                        }
                        System.out.println("Has been added " + animal);
                        cell.animals.add(animal);
                    }
                }
            }
        }
    }

    public static synchronized void printMap() {
        System.out.println(unmodifiableIslandMap);
    }

    @Override
    public void run() {
        day++;
        System.out.println("\nDay " + day + "\nCurrent statistics:");
        printMap();
        Simulation.getPhaser().arriveAndAwaitAdvance();
    }

    public static class Cell {
        private final Position position;
        private final Map<BiosphereTypes, Integer> amountBiospheresByType = new ConcurrentHashMap<>() {
            @Override
            public String toString(){
                StringBuilder sb = new StringBuilder();
                forEach((key, value) -> sb.append(key.getUnicode()).append("=").append(value).append(";\t\t"));
                return sb.toString();
            }
        };
        private List<Animal> animals = new ArrayList<>();
        private List<Plant> plants = new ArrayList<>();
        private final List<Plant> unmodifiablePlants = Collections.unmodifiableList(plants);

        public Cell(Integer x, Integer y) {
            position = new Position(x, y);
        }

        public Integer getX() {
            return position.getX();
        }

        public Integer getY() {
            return position.getY();
        }

        public Position getPosition() {
            return position;
        }

        public synchronized Integer getBiospheresAmountByType(BiosphereTypes biosphereType) {
            return amountBiospheresByType.get(biosphereType);
        }
        public synchronized Map<BiosphereTypes, Integer> getBiospheresByType() {
            return amountBiospheresByType;
        }
        public synchronized void updateBiospheresAmountByType(BiosphereTypes biosphereType, Integer amount) {
            amountBiospheresByType.put(biosphereType, amount);
        }

        public List<Animal> getAnimals() {
            return animals;
        }
        public List<Plant> getPlants() {
            return unmodifiablePlants;
        }

        // Actual method adding biosphere into cell
        public void addBiosphere(Biosphere biosphere) {
            BiosphereTypes biosphereType = biosphere.getType();
            Integer amountToAdd = generateSomeBiosphere(biosphere);
            amountBiospheresByType.put(biosphereType, amountToAdd);
            if (biosphere instanceof Animal) {
                animals.add((Animal) biosphere);
            } else {
                plants.add( (Plant) biosphere);
            }
            biosphere.setPosition(position);
        }

        //Generate some count of biosphere and check for limits
        private Integer generateSomeBiosphere(Biosphere biosphere) {
            Integer randomGeneratedAmount = ThreadLocalRandom.current().nextInt(0, biosphere.getMaxMultiplying());
            System.out.println("Has been generate " + biosphere.getType().getUnicode() + " " + randomGeneratedAmount
                    + " count");
            if (getBiospheresAmountByType(biosphere.getType()) == null) {
                return randomGeneratedAmount;
            }
            Integer biosphereCurrentAmountByType = getBiospheresAmountByType(biosphere.getType());
            Integer newAmountOfBiosphere = biosphereCurrentAmountByType + randomGeneratedAmount;
            Integer amountToAdd = ( newAmountOfBiosphere > biosphere.getMaxCountInCell() )
                    ? biosphere.getMaxCountInCell() : newAmountOfBiosphere;
            return amountToAdd;
        }

        @Override
        public String toString() {
            return "Cell {\t\t" +
                    "coordinates: " +
                    "x=" + getX() +
                    ", y=" + getY() +
                    ",\t\t biospheres in cell: \t" + amountBiospheresByType +
                    '}' + "\n";
        }

        public static class Position {
            private Integer x;
            private Integer y;

            public Position(Integer x, Integer y) {
                this.x = x;
                this.y = y;
            }

            public Integer getX() {
                return x;
            }

            public Integer getY() {
                return y;
            }

            public void setX(Integer x) {
                this.x = x;
            }

            public void setY(Integer y) {
                this.y = y;
            }

            @Override
            public String toString() {
                return "Position{" +
                        "x=" + getX() +
                        ", y=" + getY() +
                        '}' + "\n";
            }
        }
    }
}
