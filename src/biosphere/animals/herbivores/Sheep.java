package biosphere.animals.herbivores;

import biosphere.BiosphereTypes;

public class Sheep extends Herbivore {
    private static final BiosphereTypes TYPE = BiosphereTypes.SHEEP;
    private static final Integer MAX_COUNT_IN_CELL = 140;
    private static final Integer MAX_MOVE_DISTANCE = 3;
    private static final Integer MAX_MULTIPLYING = 5;
    private static final Double MAX_FOOD_TO_SATIATE = 15.0;
    private static final Double START_WEIGHT = 70.0;
    public static Integer currentCount = 0;

    public Sheep() {
        super(TYPE, MAX_COUNT_IN_CELL, MAX_FOOD_TO_SATIATE, START_WEIGHT, MAX_MULTIPLYING, MAX_MOVE_DISTANCE);
        Sheep.currentCount++;
    }
}
