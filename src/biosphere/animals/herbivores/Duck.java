package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;

public class Duck extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.DUCK;
    private static final Integer MAX_COUNT_IN_CELL = 200;
    private static final Integer MAX_MOVE_DISTANCE = 4;
    private static final Integer MAX_MULTIPLYING = 5;
    private static final Double MAX_FOOD_TO_SATIATE = 0.15;
    private static final Double START_WEIGHT = 1.0;

    public static Integer currentCount = 0;

    public Duck() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Duck.currentCount++;
    }
}
