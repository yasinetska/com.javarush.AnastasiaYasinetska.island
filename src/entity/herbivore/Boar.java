package entity.herbivore;

import entity.Entity;
import islandSimulation.Cell;
import settings.FoodProbabilities;

import java.util.Map;

public class Boar extends Herbivore{
    public Boar(Cell cell) {
        super(cell);
    }

    public Map<Class<? extends Entity>, Double> getFoodProbability() {
        return FoodProbabilities.BOAR_PROBABILITIES;
    }
}
