package simulatorProperties;

import biosphere.Animal;
import biosphere.animals.herbivores.Sheep;
import biosphere.animals.predators.Fox;
import biosphere.animals.predators.Wolf;

import java.util.ArrayList;
import java.util.List;

public class SimulationProperties {

    // Program params
    public static final int TIME_ON_STEP_IN_MILLISECONDS = 3000;
    public static final int TIME_ON_STEP_IN_SECONDS = TIME_ON_STEP_IN_MILLISECONDS / 1000;
    public static final int THREADS_COUNT = 1;

    // Island params
    public static final int ISLAND_HEIGHT = 3;
    public static final int ISLAND_WIDTH = 3;

    // Cell param
    public static final int MAX_ANIMALS_IN_CELL_ON_START = 120;
    public static final int MAX_PLANTS_IN_CELL_ON_START = 100;

    // Biosphere params
    private static final int HERBIVORES_KINDS_STARTING_AMOUNT = 10;
    public static final int HERBIVORES_STARTING_AMOUNT = HERBIVORES_KINDS_STARTING_AMOUNT * 2;

    private static final int PREDATORS_KINDS_STARTING_AMOUNT = 5;
    public static final int PREDATORS_STARTING_AMOUNT = PREDATORS_KINDS_STARTING_AMOUNT * 2;

    private static final int PLANTS_KINDS_STARTING_AMOUNT = 2;
    public static final int PLANTS_STARTING_AMOUNT = PLANTS_KINDS_STARTING_AMOUNT * 20;


    // Animals params
    public static final double HEALTH_PERCENT_DECREASE_FROM_HUNGER = 0.1;

    public static List<Animal> startAnimalsPopulation = new ArrayList<>(40) {{
        add(new Wolf());
        add(new Wolf());
        add(new Wolf());
        add(new Wolf());
        add(new Fox());
        add(new Fox());
        add(new Fox());
        add(new Fox());
        add(new Sheep());
        add(new Sheep());
        add(new Sheep());
        add(new Sheep());
    }};

    // Plants params
    public static final int PERCENT_OF_CHANCE_NEW_PLANTS_GROW_IN_CELL = 15;
}
