package entity.predator;

import entity.Animal;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.Map;

public class Bear extends Predator{
    public Bear(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Animal>, Double> getFoodProbability() {
        return FoodProbabilities.BEAR_PROBABILITIES;
    }
}
