package entity.herbivore;

import entity.Entity;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.Map;

public class Mouse extends Herbivore{
    public Mouse(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Entity>, Double> getFoodProbability() {
        return FoodProbabilities.MOUSE_PROBABILITIES;
    }
}
