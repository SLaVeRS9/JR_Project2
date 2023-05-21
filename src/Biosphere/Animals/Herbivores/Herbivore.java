package Biosphere.Animals.Herbivores;

import Biosphere.*;
import Biosphere.BiosphereTypes;
import Island.Island;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Herbivore extends Animal {
    protected Herbivore(BiosphereTypes type, Integer maxCountInCell, Double maxFoodToSatiate, Double startWeight, Integer maxMultiplying, Integer maxMoveDistance) {
        super(type, maxCountInCell, maxFoodToSatiate, startWeight, maxMultiplying, maxMoveDistance);
    }

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
    @Override
    public void eat() {
        System.out.println("HERBIVORE START EAT");
        Island.Cell currentCell = Island.getCell(getCurrentPosition());
        Optional<Plant> optionalPlant = findPlant();
        if (optionalPlant.isPresent()) {
            System.out.println("HERBIVORE EAT PLANT");
            Plant plant = optionalPlant.get();
            Integer plantsAmount = currentCell.getBiospheresAmountByType(plant.getType());
            System.out.println("plantsAmount " + plantsAmount);
            Double neededFood = MAX_FOOD_TO_SATIATE - currentSatiety;
            System.out.println("neededFood " + neededFood);
            Double plantsCanBeEaten = ( plantsAmount * plant.getWeight() ) > neededFood ? neededFood / plant.getWeight() : plantsAmount * plant.getWeight();
            System.out.println("plantsCanBeEaten " + plantsCanBeEaten);
            currentSatiety = plantsCanBeEaten * plant.getWeight();
            System.out.println("currentSatiety " + currentSatiety);
            Integer remainingPlants = plantsAmount - plantsCanBeEaten.intValue();
            System.out.println("remainingPlants " + remainingPlants);
            currentCell.updateBiospheresAmountByType(plant.getType(), remainingPlants);
            currentCell.getPlants().remove(plant);
        } else {
            super.eat();
        }
    }
}
