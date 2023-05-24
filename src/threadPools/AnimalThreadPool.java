package threadPools;

import biosphere.Animal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnimalThreadPool {
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    public static void startAPool(Animal animal) {
        executorService.submit(animal);
    }

    public static void stopAPool() {
        executorService.shutdown();
    }
}
