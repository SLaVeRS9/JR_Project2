package Biosphere.Animals.Herbivores;

import Biosphere.*;
import Island.Island;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivore extends Animal {
    protected Herbivore(BiosphereTypes type, Integer maxCountInCell, Double maxFoodToSatiate, Double startWeight, Integer maxMultiplying, Integer maxMoveDistance) {
        super(type, maxCountInCell, maxFoodToSatiate, startWeight, maxMultiplying, maxMoveDistance);
    }

    // Find plant to eat
    public Optional<Plant> findPlant() {
        Island.Cell.Position currentPosition = getCurrentPosition();
        Island.Cell currentCell = Island.getCell(currentPosition);
        List<Plant> plants = currentCell.getPlants();
        if (!plants.isEmpty()) {
            Integer randomPlantIndex = ThreadLocalRandom.current().nextInt(plants.size());
            return Optional.ofNullable(plants.get(randomPlantIndex)) ;
        } else {
            return Optional.empty() ;
        }
    }

    // Eat method to eat plant or other animal
    @Override
    public void eat() {
        Island.Cell currentCell = Island.getCell(getCurrentPosition());
        Optional<Plant> optionalPlant = findPlant();
        if (optionalPlant.isPresent()) {
            Plant plant = optionalPlant.get();
            Integer plantsAmount = currentCell.getBiospheresAmountByType(plant.getType());
            Double neededFood = MAX_FOOD_TO_SATIATE - currentSatiety;
            Double plantsCanBeEaten = ( plantsAmount * plant.getWeight() ) > neededFood ? neededFood / plant.getWeight() : plantsAmount * plant.getWeight();
            currentSatiety = plantsCanBeEaten * plant.getWeight();
            Integer remainingPlants = plantsAmount - plantsCanBeEaten.intValue();
            currentCell.updateBiospheresAmountByType(plant.getType(), remainingPlants);
            currentCell.getPlants().remove(plant);
            System.out.println("Herbivore " + this.getType().getUnicode() + " has been eat " + plantsCanBeEaten + " " + plant.getType().getUnicode());
        } else {
            super.eat();
        }
    }
}
