package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;

public class Caterpillar extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.CATERPILLAR;
    private static final Integer MAX_COUNT_IN_CELL = 1000;
    private static final Integer MAX_MOVE_DISTANCE = 0;
    private static final Integer MAX_MULTIPLYING = 80;
    private static final Double MAX_FOOD_TO_SATIATE = 0.0;
    private static final Double START_WEIGHT = 0.01;

    public static Integer currentCount = 0;

    public Caterpillar() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Caterpillar.currentCount++;
    }
}
