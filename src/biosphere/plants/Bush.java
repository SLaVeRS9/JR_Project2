package biosphere.plants;

import biosphere.BiosphereTypes;
import biosphere.Plant;

public class Bush extends Plant {
    private static final BiosphereTypes TYPE = BiosphereTypes.BUSH;
    private static final Integer MAX_COUNT_IN_CELL = 200;
    private static final Integer MAX_MULTIPLYING = 30;
    private static final Double START_WEIGHT = 1.0;
    public static Integer currentCount = 0;

    public Bush() {
        super(TYPE, MAX_COUNT_IN_CELL, START_WEIGHT, MAX_MULTIPLYING);
        Bush.currentCount++;
    }
}
