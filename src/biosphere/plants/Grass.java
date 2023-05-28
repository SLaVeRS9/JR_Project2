package biosphere.plants;

import biosphere.BiosphereTypes;
import biosphere.Plant;

public class Grass extends Plant {
    private static final BiosphereTypes TYPE = BiosphereTypes.GRASS;
    private static final Integer MAX_COUNT_IN_CELL = 200;
    private static final Integer MAX_MULTIPLYING = 30;
    private static final Double START_WEIGHT = 0.1;
    public static Integer currentCount = 0;

    public Grass() {
        super(TYPE, MAX_COUNT_IN_CELL, START_WEIGHT, MAX_MULTIPLYING);
        Grass.currentCount++;
    }
}
