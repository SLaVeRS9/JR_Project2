package biosphere.animals.predators;

import biosphere.BiosphereTypes;

public class Fox extends Predator {
    private static final BiosphereTypes TYPE = BiosphereTypes.FOX;
    private static final Integer MAX_COUNT_IN_CELL = 30;
    private static final Integer MAX_MOVE_DISTANCE = 2;
    private static final Integer MAX_MULTIPLYING = 3;
    private static final Double MAX_FOOD_TO_SATIATE = 2.0;
    private static final Double START_WEIGHT = 8.0;
    public static Integer currentCount = 0;

    public Fox() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Fox.currentCount++;
    }
}
