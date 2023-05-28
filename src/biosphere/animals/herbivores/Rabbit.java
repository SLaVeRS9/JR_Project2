package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;

public class Rabbit extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.RABBIT;
    private static final Integer MAX_COUNT_IN_CELL = 150;
    private static final Integer MAX_MOVE_DISTANCE = 2;
    private static final Integer MAX_MULTIPLYING = 10;
    private static final Double MAX_FOOD_TO_SATIATE = 0.45;
    private static final Double START_WEIGHT = 2.0;

    public static Integer currentCount = 0;

    public Rabbit() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Rabbit.currentCount++;
    }
}
