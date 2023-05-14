package Biosphere;

import Biosphere.Plants.Bush;
import Biosphere.Plants.Grass;
import Island.IslandMap;
import SimulatorProperties.SimulationProperties;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Plant extends Biosphere implements Runnable {

    protected Integer maxMultiplying;

    public Integer getMaxMultiplying(){
        return maxMultiplying;
    }

    //Grow new plants on the all island
    protected void grow() {
        System.out.println("\n\tStart grow iteration");
        //TODO Добвить проверку на возможность расти исходя из предела растения на клетку
        List<List<IslandMap.Cell>> unmodifiableIslandMap = IslandMap.getIslandMap();
        for (int i = 0; i < SimulationProperties.ISLAND_WIDTH; i++) {
            for (int j = 0; j < SimulationProperties.ISLAND_HEIGHT; j++) {
                IslandMap.Cell cell = unmodifiableIslandMap.get(i).get(j);
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
                    System.out.println("is limit");
                    continue;
                }
                cell.addBiosphere(createdPlant);
                System.out.printf("Added plant: %s\t into cell: x=%s y=%s%n", createdPlant.getType().getUnicode(), i, j);
            }
        }
    }

    protected boolean checkPlantForLimitCountInCell(IslandMap.Cell cell, Biosphere biosphere) {
        System.out.println("Check limit");
        Integer biospheresAmountByType = cell.getBiospheresAmountByType(biosphere.getType());
        System.out.println(biospheresAmountByType);
        if (biospheresAmountByType == null) {
            System.out.println("getBiospheresAmountByType = " + biospheresAmountByType);
            return false;
        }
        System.out.println("CHECK");
        return biospheresAmountByType >= biosphere.maxCountInCell;
    }


    //Create any kind of Plant
    private static Optional<Plant> createPlant() {
        Random random = new Random();
        Plant creatingPlant = switch (random.nextInt(2)) {
            case 0 -> new Bush();
            case 1 -> new Grass();
            default -> null;
        };
        System.out.println("Plant has been created: " + creatingPlant.getType().getUnicode());
        return Optional.ofNullable(creatingPlant);
    }

    private static boolean plantCanBeGrow() {
        int random = ThreadLocalRandom.current().nextInt(100);
        return random <= SimulationProperties.PERCENT_OF_CHANCE_NEW_PLANTS_GROW_IN_CELL;
    }

    @Override
    public void run() {
        grow();
    }
}