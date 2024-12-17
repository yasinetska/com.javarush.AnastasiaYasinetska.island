package entity.predator;

import entity.Animal;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.Map;

public class Eagle extends Predator {
    public Eagle(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Animal>, Double> getFoodProbability() {
        return FoodProbabilities.EAGLE_PROBABILITIES;
    }
}
