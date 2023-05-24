package threadPools;

import biosphere.Animal;
import island.Island;
import simulation.Simulation;
import simulatorProperties.SimulationProperties;
import java.util.List;
import java.util.concurrent.*;

public class AnimalTask implements Runnable {
    private static Integer iteration = 0;
    private static List<Animal> animals = new CopyOnWriteArrayList<>();

    @Override
    public void run() {
        Simulation.getPhaser().arriveAndAwaitAdvance();
        Island.Cell randomCell = Island.getRandomCell();
        List<Animal> animals = randomCell.getAnimals();
        Integer animalsCount = Math.min(animals.size(), SimulationProperties.THREADS_COUNT);
        Animal animal = null;
        for (int i = 0; i < animalsCount; i++) {
            randomCell = Island.getRandomCell();
            animals = randomCell.getAnimals();
            while (animals.size() == 0 && iteration < 10) {
                randomCell = Island.getRandomCell();
                animals = randomCell.getAnimals();
                iteration++;
            }
            if(animals.size() > 0) {
                Integer randomBiosphereIndex = ThreadLocalRandom.current().nextInt(animals.size());
                animal = animals.get(randomBiosphereIndex);
                System.out.print("Has been chosen animal " + animal.getType().getUnicode() + " in cell " + animal.getCurrentCell());
            }
            AnimalTask.animals.add(animal);
        }
        // Logic for many threads
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = AnimalTask.animals.size() - 1; i >= 0; i--) {
            executorService.submit(AnimalTask.animals.remove(i));
        }
    }
}
