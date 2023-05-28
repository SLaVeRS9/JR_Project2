package biosphere.animals.predators;

import biosphere.BiosphereTypes;

public class Snake extends Predator {
    private static final BiosphereTypes TYPE = BiosphereTypes.SNAKE;
    private static final Integer MAX_COUNT_IN_CELL = 30;
    private static final Integer MAX_MOVE_DISTANCE = 1;
    private static final Integer MAX_MULTIPLYING = 3;
    private static final Double MAX_FOOD_TO_SATIATE = 3.0;
    private static final Double START_WEIGHT = 15.0;

    public static Integer currentCount = 0;

    public Snake() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Snake.currentCount++;
    }
}
