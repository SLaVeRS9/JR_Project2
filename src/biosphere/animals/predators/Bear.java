package biosphere.animals.predators;

import biosphere.BiosphereTypes;

public class Bear extends Predator {
    private static final BiosphereTypes TYPE = BiosphereTypes.BEAR;
    private static final Integer MAX_COUNT_IN_CELL = 5;
    private static final Integer MAX_MOVE_DISTANCE = 2;
    private static final Integer MAX_MULTIPLYING = 1;
    private static final Double MAX_FOOD_TO_SATIATE = 80.0;
    private static final Double START_WEIGHT = 500.0;

    public static Integer currentCount = 0;

    public Bear() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Bear.currentCount++;
    }
}
