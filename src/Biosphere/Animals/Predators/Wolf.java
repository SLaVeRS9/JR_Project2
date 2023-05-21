package Biosphere.Animals.Predators;

import Biosphere.BiosphereTypes;

public class Wolf extends Predator {
    private static final BiosphereTypes TYPE = BiosphereTypes.WOLF;
    private static final Integer MAX_COUNT_IN_CELL = 30;
    private static final Integer MAX_MOVE_DISTANCE = 3;
    private static final Integer MAX_MULTIPLYING = 3;
    private static final Double MAX_FOOD_TO_SATIATE = 8.0;
    private static final Double START_WEIGHT = 50.0;

    public static Integer currentCount = 0;

    public Wolf() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Wolf.currentCount++;
    }
}
