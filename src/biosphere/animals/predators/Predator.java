package biosphere.animals.predators;

import biosphere.Animal;
import biosphere.BiosphereTypes;

public abstract class Predator extends Animal {
    protected Predator(BiosphereTypes type, Integer maxCountInCell, Double maxFoodToSatiate, Double startWeight, Integer maxMultiplying, Integer maxMoveDistance) {
        super(type, maxCountInCell, maxFoodToSatiate, startWeight, maxMultiplying, maxMoveDistance);
    }
}
