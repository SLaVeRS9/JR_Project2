package simulation;

import biosphere.Plant;
import island.Island;
import simulatorProperties.SimulationProperties;
import threadPools.*;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private static Phaser phaser = new Phaser(3);
    public static void start() throws InterruptedException {
        System.out.println("Hello!\nWelcome to the island!");
        System.out.println("--> If you want to stop simulation, enter any world <--");
        Thread.sleep(3000);
        Scanner input = new Scanner(System.in);
        Island.generateMap();

        ScheduledExecutorService printStatisticsScheduler = Executors.newScheduledThreadPool(1);
        printStatisticsScheduler.scheduleAtFixedRate(Island.getIsland(), 0, SimulationProperties.TIME_ON_STEP_IN_SECONDS, TimeUnit.SECONDS);

        ScheduledExecutorService growPlantsScheduler = Executors.newScheduledThreadPool(1);
        Thread thread = new Thread(Plant.getRandomPlant());
        growPlantsScheduler.scheduleAtFixedRate(thread, 0, SimulationProperties.TIME_ON_STEP_IN_SECONDS, TimeUnit.SECONDS);

        ScheduledExecutorService animalTaskScheduler = Executors.newScheduledThreadPool(1);
        animalTaskScheduler.scheduleAtFixedRate(new AnimalTask(), 0, SimulationProperties.TIME_ON_STEP_IN_SECONDS, TimeUnit.SECONDS);

        String exit = input.nextLine();
    }

    public static Phaser getPhaser() {
        return phaser;
    }

}
