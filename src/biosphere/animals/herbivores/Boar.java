package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;

public class Boar extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.BOAR;
    private static final Integer MAX_COUNT_IN_CELL = 50;
    private static final Integer MAX_MOVE_DISTANCE = 2;
    private static final Integer MAX_MULTIPLYING = 2;
    private static final Double MAX_FOOD_TO_SATIATE = 50.0;
    private static final Double START_WEIGHT = 400.0;

    public static Integer currentCount = 0;

    public Boar() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Boar.currentCount++;
    }
}
