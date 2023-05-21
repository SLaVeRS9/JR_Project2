package Biosphere.Animals.Predators;

import Biosphere.Animal;
import Biosphere.BiosphereTypes;

public abstract class Predator extends Animal {
    protected Predator(BiosphereTypes type, Integer maxCountInCell, Double maxFoodToSatiate, Double startWeight, Integer maxMultiplying, Integer maxMoveDistance) {
        super(type, maxCountInCell, maxFoodToSatiate, startWeight, maxMultiplying, maxMoveDistance);
    }
}
