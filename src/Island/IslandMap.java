package Island;

import Biosphere.Animal;
import Biosphere.BiosphereTypes;
import Biosphere.Animals.Herbivores.Sheep;
import Biosphere.Animals.Predators.Fox;
import Biosphere.Animals.Predators.Wolf;
import Biosphere.Biosphere;
import Biosphere.Plants.Bush;
import Biosphere.Plants.Grass;
import Biosphere.Plant;
import SimulatorProperties.SimulationProperties;
import java.util.*;

public class IslandMap {
    private static int islandMapSize = SimulationProperties.ISLAND_HEIGHT * SimulationProperties.ISLAND_WIDTH; //TODO Maybe delete this
    private static final List<List<Cell>> islandMap = new ArrayList<>(SimulationProperties.ISLAND_WIDTH);
    private static final List<List<Cell>> unmodifiableIslandMap = Collections.unmodifiableList(islandMap);

    public static List<List<Cell>> getIslandMap() {
        return unmodifiableIslandMap;
    }
    
    //Generate island map to use
    public static void generateMap(){
        createMap();
        
        //Add biosphere to the map
        addBiosphere();

        //Print map
        printMap();
    }
    
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

    //Add biosphere on all island map
    private static void addBiosphere(){
        Stack<Biosphere> tempBiosphere = new Stack<>();
        //TODO сделать ограничение на кол-во создаваемых сразу хищников и травоядных
        /*Integer predatorsStartingAmount = SimulationProperties.PREDATORS_STARTING_AMOUNT;
        Integer herbivoresStartingAmount = SimulationProperties.HERBIVORES_STARTING_AMOUNT;*/

        for (int i = 0; i < SimulationProperties.ISLAND_WIDTH; i++) {
            for (int j = 0; j < SimulationProperties.ISLAND_HEIGHT; j++) {
                Cell currentCell = IslandMap.islandMap.get(i).get(j);
                int animalsCountInCell = new Random().nextInt(SimulationProperties.MAX_ANIMALS_IN_CELL_ON_START);
                for (int k = 0; k < animalsCountInCell; k++) {
                    Optional<Animal> optionalCreatingAnimal = createAnimal();
                    if (optionalCreatingAnimal.isEmpty()) {
                        continue;
                    }
                    Animal createdAnimal = optionalCreatingAnimal.get();
                    createdAnimal.setCurrentPosition(currentCell.position);
                    currentCell.addBiosphere(optionalCreatingAnimal.get()); //TODO сделать ограничение на кол-во в ячейке
                }

                int plantsCountInCell = new Random().nextInt(SimulationProperties.MAX_PLANTS_IN_CELL_ON_START);
                for (int k = 0; k < plantsCountInCell; k++) {
                    Optional<Plant> optionalCreatingPlant = createPlant();
                    if (optionalCreatingPlant.isEmpty()) {
                        continue;
                    }
                    Plant createdPlant = optionalCreatingPlant.get();
                    createdPlant.setCurrentPosition(currentCell.position);
                    currentCell.addBiosphere(optionalCreatingPlant.get()); //TODO сделать ограничение на кол-во в ячейке
                }

            }
        }
    }

    private static Optional<Animal> createAnimal() {
        Random random = new Random();
        Animal creatingAnimal = switch (random.nextInt(3)) {
            case 0 -> new Wolf();
            case 1 -> new Fox();
            case 2 -> new Sheep();
            default -> null;
        };
        return Optional.ofNullable(creatingAnimal);
    }

    private static Optional<Plant> createPlant() {
        Random random = new Random();
        Plant creatingPlant = switch (random.nextInt(2)) {
            case 0 -> new Bush();
            case 1 -> new Grass();
            default -> null;
        };
        return Optional.ofNullable(creatingPlant);
    }

    public static void printMap() {
        System.out.println(unmodifiableIslandMap);
    }

    public static class Cell {
        private final Position position;
        private Map<BiosphereTypes, Integer> amountBiospheresByType = new HashMap<>() {
            @Override
            public String toString(){
                StringBuilder sb = new StringBuilder();
                forEach((key, value) -> sb.append(key.getUnicode()).append("=").append(value).append(";\t\t"));
                return sb.toString();
            }
        };
        private List<Biosphere> biospheres = new ArrayList<>();
        private final List<Biosphere> unmodifiableBiospheres = Collections.unmodifiableList(biospheres);

        public Cell(Integer x, Integer y) {
            position = new Position(x, y);
        }

        public Integer getX() {
            return position.getX();
        }

        public Integer getY() {
            return position.getY();
        }

        public Integer getBiospheresAmountByType(BiosphereTypes biosphereType) {
            return amountBiospheresByType.get(biosphereType);
        }

        public List<Biosphere> getBiospheres() {
            return unmodifiableBiospheres;
        }

        //Add biosphere in cell
        void addBiosphere(Biosphere biosphere) {
            //TODO add current position to biosphere
            BiosphereTypes biosphereType = biosphere.getType();
            if (amountBiospheresByType.containsKey(biosphereType)) {
                Integer currentAmount = amountBiospheresByType.get(biosphereType);
                amountBiospheresByType.put(biosphereType, ++currentAmount);
            } else {
                amountBiospheresByType.put(biosphereType, 1);
            }
            biospheres.add(biosphere);
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
            private final Integer x;
            private final Integer y;

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
