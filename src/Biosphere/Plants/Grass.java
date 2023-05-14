package Biosphere.Plants;

import Biosphere.BiosphereTypes;
import Biosphere.Plant;

public class Grass extends Plant {
    private static final Integer MAX_COUNT_IN_CELL = 200;
    private static final Integer MAX_MULTIPLYING = 30;
    public static Integer currentCount = 0;

    public Grass() {
        weight = 0.1;
        type = BiosphereTypes.GRASS;
        maxCountInCell = MAX_COUNT_IN_CELL;
        maxMultiplying = MAX_MULTIPLYING;
        Grass.currentCount++;
    }

    @Override
    protected void grow() {
        super.grow();
    }

    @Override
    public String toString() {
        return "Grass{" +
                "weight=" + weight +
                '}';
    }

}
