package entity.predator;

import entity.Animal;
import islandSimulation.Cell;
import lombok.Getter;
import settings.FoodProbabilities;
import java.util.Map;

@Getter
public class Wolf extends Predator {
    public Wolf(Cell cell) {
        super(cell);
    }

    @Override
    public Map<Class<? extends Animal>, Double> getFoodProbability() {
        return FoodProbabilities.WOLF_PROBABILITIES;
    }
}
