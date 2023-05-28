package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;


public class Deer extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.DEER;
    private static final Integer MAX_COUNT_IN_CELL = 20;
    private static final Integer MAX_MOVE_DISTANCE = 4;
    private static final Integer MAX_MULTIPLYING = 1;
    private static final Double MAX_FOOD_TO_SATIATE = 50.0;
    private static final Double START_WEIGHT = 300.0;

    public static Integer currentCount = 0;

    public Deer() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Deer.currentCount++;
    }
}
