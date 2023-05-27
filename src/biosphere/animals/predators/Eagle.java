package biosphere.animals.predators;

import biosphere.BiosphereTypes;

public class Eagle extends Predator {
    private static final BiosphereTypes TYPE = BiosphereTypes.EAGLE;
    private static final Integer MAX_COUNT_IN_CELL = 20;
    private static final Integer MAX_MOVE_DISTANCE = 3;
    private static final Integer MAX_MULTIPLYING = 1;
    private static final Double MAX_FOOD_TO_SATIATE = 1.0;
    private static final Double START_WEIGHT = 6.0;

    public static Integer currentCount = 0;

    public Eagle() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Eagle.currentCount++;
    }
}
