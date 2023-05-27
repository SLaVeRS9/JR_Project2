package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;

public class Mouse extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.MOUSE;
    private static final Integer MAX_COUNT_IN_CELL = 500;
    private static final Integer MAX_MOVE_DISTANCE = 1;
    private static final Integer MAX_MULTIPLYING = 30;
    private static final Double MAX_FOOD_TO_SATIATE = 0.01;
    private static final Double START_WEIGHT = 0.05;

    public static Integer currentCount = 0;

    public Mouse() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Mouse.currentCount++;
    }
}
