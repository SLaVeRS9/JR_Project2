package Biosphere.Plants;

import Biosphere.BiosphereTypes;
import Biosphere.Plant;

public class Bush extends Plant {
    private static final Integer MAX_COUNT_IN_CELL = 200;
    private static final Integer MAX_MULTIPLYING = 30;

    public static Integer currentCount = 0;
    public Bush() {
        maxCountInCell = MAX_COUNT_IN_CELL;
        maxMultiplying = MAX_MULTIPLYING;
        weight = 1.0;
        type = BiosphereTypes.BUSH;
        Bush.currentCount++;
    }

    @Override
    public String toString() {
        return "Bush{" +
                "weight=" + weight +
                '}';
    }
}
