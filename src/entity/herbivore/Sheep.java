package entity.herbivore;

import entity.Entity;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.Map;

public class Sheep extends Herbivore{
    public Sheep(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Entity>, Double> getFoodProbability() {
        return FoodProbabilities.SHEEP_PROBABILITIES;
    }
}
