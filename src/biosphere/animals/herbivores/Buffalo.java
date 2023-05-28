package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;

public class Buffalo extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.BUFFALO;
    private static final Integer MAX_COUNT_IN_CELL = 10;
    private static final Integer MAX_MOVE_DISTANCE = 3;
    private static final Integer MAX_MULTIPLYING = 1;
    private static final Double MAX_FOOD_TO_SATIATE = 100.0;
    private static final Double START_WEIGHT = 700.0;

    public static Integer currentCount = 0;

    public Buffalo() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Buffalo.currentCount++;
    }
}
