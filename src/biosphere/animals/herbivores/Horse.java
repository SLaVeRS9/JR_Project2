package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;

public class Horse extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.HORSE;
    private static final Integer MAX_COUNT_IN_CELL = 20;
    private static final Integer MAX_MOVE_DISTANCE = 4;
    private static final Integer MAX_MULTIPLYING = 1;
    private static final Double MAX_FOOD_TO_SATIATE = 60.0;
    private static final Double START_WEIGHT = 400.0;

    public static Integer currentCount = 0;

    public Horse() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Horse.currentCount++;
    }
}
