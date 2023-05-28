package threadPools;

import biosphere.Animal;
import island.Island;
import simulation.Simulation;
import simulatorProperties.SimulationProperties;
import java.util.List;
import java.util.concurrent.*;

public class AnimalTask implements Runnable {
    private static List<Animal> animalsToStart = new CopyOnWriteArrayList<>();
    private static List<Island.Cell> cellsWithAnimals = new CopyOnWriteArrayList<>();

    @Override
    public void run() {
        Simulation.getPhaser().arriveAndAwaitAdvance();
        List<List<Island.Cell>> islandMap = Island.getIslandMap();
        for (List<Island.Cell> cells : islandMap) {
            for (Island.Cell cell : cells) {
                if (cell.getAnimals().size() > 0) {
                    cellsWithAnimals.add(cell);
                }
            }
        }

        Integer cellsWithAnimalsCount = Math.min(cellsWithAnimals.size(), SimulationProperties.THREADS_COUNT);
        for (int i = 0; i < cellsWithAnimalsCount; i++) {
            Integer randomCellPosition = ThreadLocalRandom.current().nextInt(0, cellsWithAnimals.size());
            Island.Cell randomCell = cellsWithAnimals.get(randomCellPosition);
            if(randomCell.getAnimals().size() < 1) {
                cellsWithAnimals.remove(randomCell);
                continue;
            }
            List<Animal> animals = randomCell.getAnimals();
            Integer randomAnimalIndex = ThreadLocalRandom.current().nextInt(animals.size());
            Animal animal = animals.get(randomAnimalIndex);
            if (animalsToStart.contains(animal)) {
                continue;
            }
            System.out.println("Has been chosen animal " + animal + " in cell " + animal.getCurrentCell().getPosition());
            AnimalTask.animalsToStart.add(animal);
        }

        // Logic for many threads
        for (Animal animal : animalsToStart) {
            AnimalThreadPool.startAPool(animal);
        }
        animalsToStart.clear();
        cellsWithAnimals.clear();
    }
}
