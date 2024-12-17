package entity.herbivore;

import entity.Entity;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.Map;

public class Rabbit extends Herbivore {
    public Rabbit(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Entity>, Double> getFoodProbability() {
        return FoodProbabilities.RABBIT_PROBABILITIES;
    }
}
