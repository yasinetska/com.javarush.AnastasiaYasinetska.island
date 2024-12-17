package entity.predator;

import entity.Animal;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.Map;

public class Fox extends Predator {
    public Fox(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Animal>, Double> getFoodProbability() {
        return FoodProbabilities.FOX_PROBABILITIES;
    }
}
