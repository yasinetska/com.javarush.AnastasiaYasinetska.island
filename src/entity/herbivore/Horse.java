package entity.herbivore;

import entity.Entity;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.Map;

public class Horse extends Herbivore{
    public Horse(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Entity>, Double> getFoodProbability() {
        return FoodProbabilities.HORSE_PROBABILITIES;
    }
}
