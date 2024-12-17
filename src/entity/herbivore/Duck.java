package entity.herbivore;

import entity.Animal;
import entity.Entity;
import islandSimulation.Cell;
import settings.FoodProbabilities;
import java.util.List;
import java.util.Map;

public class Duck extends Herbivore {
    public Duck(Cell cell) {
        super(cell);
    }

    @Override
    public void eat(Cell cell) {
        super.eat(cell);

        List<Animal> caterpillars = cell.getAnimals().stream()
                .filter(animal -> animal instanceof Caterpillar && animal.isAlive())
                .toList();
        if (!caterpillars.isEmpty() && Math.random() < 0.9) {
            Animal caterpillar = caterpillars.get(0);
            caterpillar.die();
            increaseSatiation(caterpillar.getMaxWeight());
        }
    }

    @Override
    public Map<Class<? extends Entity>, Double> getFoodProbability() {
        return FoodProbabilities.DUCK_PROBABILITIES;
    }
}
