package entity.predator;

import entity.Animal;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.Map;

public class Boa extends Predator{
    public Boa(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Animal>, Double> getFoodProbability() {
        return FoodProbabilities.BOA_PROBABILITIES;
    }
}
