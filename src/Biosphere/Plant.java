package Biosphere;

import Biosphere.Plants.Bush;
import Biosphere.Plants.Grass;
import Island.Island;
import Simulation.Simulation;
import SimulatorProperties.SimulationProperties;
import ThreadPools.Test;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Plant extends Biosphere implements Runnable {
    protected Plant(BiosphereTypes type, Integer maxCountInCell, Double startWeight, Integer maxMultiplying) {
        super(type, maxCountInCell, startWeight, maxMultiplying);
    }

    // Get random plant
    public static Plant getRandomPlant() {
        Optional<Plant> oPlant = createPlant();
        if (oPlant.isEmpty()) {
            return new Grass();
        }
        Plant createdPlant = oPlant.get();
        return createdPlant;
    }

    // Create any kind of Plant
    private static Optional<Plant> createPlant() {
        Random random = new Random();
        Plant creatingPlant = switch (random.nextInt(2)) {
            case 0 -> new Bush();
            case 1 -> new Grass();
            default -> null;
        };
        return Optional.ofNullable(creatingPlant);
    }

    // Check can plant be grown
    private static boolean plantCanBeGrow() {
        int random = ThreadLocalRandom.current().nextInt(100);
        return random <= SimulationProperties.PERCENT_OF_CHANCE_NEW_PLANTS_GROW_IN_CELL;
    }

    // TODO LATER Переделать на рост в рамках одной клетки, а рост по карте - вынести в карту
    // Grow new plants on the all island
    protected void grow() {
        System.out.println("\nStart grow iteration");
        List<List<Island.Cell>> unmodifiableIslandMap = Island.getIslandMap();
        for (int i = 0; i < SimulationProperties.ISLAND_WIDTH; i++) {
            for (int j = 0; j < SimulationProperties.ISLAND_HEIGHT; j++) {
                Island.Cell cell = unmodifiableIslandMap.get(i).get(j);
                if (!plantCanBeGrow()) {
                    continue;
                }
                Optional<Plant> optionalCreatingPlant = createPlant();
                if (optionalCreatingPlant.isEmpty()) {
                    continue;
                }
                Plant createdPlant = optionalCreatingPlant.get();
                boolean isLimitInCell = checkPlantForLimitCountInCell(cell, createdPlant);
                if (isLimitInCell){
                    continue;
                }
                Integer biospheresAmountByType = cell.getBiospheresAmountByType(createdPlant.getType());
                if (biospheresAmountByType == null) {
                    continue;
                }
                cell.addBiosphere(createdPlant);
                System.out.printf("Added plant: %s\t into cell: x=%s y=%s%n%n", createdPlant.getType().getUnicode(), i, j);
            }
        }
    }

    //Check plants amount limit in cell
    protected boolean checkPlantForLimitCountInCell(Island.Cell cell, Biosphere biosphere) {
        Integer biospheresAmountByType = cell.getBiospheresAmountByType(biosphere.getType());
        if (biospheresAmountByType == null) {
            return false;
        }
        return biospheresAmountByType >= biosphere.getMaxCountInCell();
    }

    @Override
    public String toString() {
        return "Plant{ " + TYPE.getUnicode() +
                "\tPosition= " + "x:" + currentPosition.getX() + " y:" + currentPosition.getY() +
                "\t, weight=" + weight +
                '}';
    }

    @Override
    public void run() {
        Simulation.getPhaser().arriveAndAwaitAdvance();
        grow();
    }
}