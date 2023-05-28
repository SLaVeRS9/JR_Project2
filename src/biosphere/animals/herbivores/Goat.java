package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;

public class Goat extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.GOAT;
    private static final Integer MAX_COUNT_IN_CELL = 140;
    private static final Integer MAX_MOVE_DISTANCE = 3;
    private static final Integer MAX_MULTIPLYING = 3;
    private static final Double MAX_FOOD_TO_SATIATE = 10.0;
    private static final Double START_WEIGHT = 60.0;

    public static Integer currentCount = 0;

    public Goat() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Goat.currentCount++;
    }
}
