package entity.herbivore;

import entity.Entity;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.Map;

public class Deer extends Herbivore{
    public Deer(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Entity>, Double> getFoodProbability() {
        return FoodProbabilities.DEER_PROBABILITIES;
    }
}
