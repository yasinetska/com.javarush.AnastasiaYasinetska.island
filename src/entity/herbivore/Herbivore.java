package entity.herbivore;

import entity.Animal;
import entity.Entity;
import islandSimulation.Cell;
import java.util.Map;

public abstract class Herbivore extends Animal {

    public Herbivore(Cell cell) {
        super(cell);
    }

    @Override
    public void eat(Cell cell) {
        int availablePlants = cell.getPlants().size();

        if (availablePlants <= 0) {
            loseEnergy();
            return;
        }

        double foodNeeded = getMaxSatiety() - getActualSatiety();
        if (foodNeeded <= 0) {
            return;
        }

        int plantsToEat = (int) Math.min(foodNeeded, availablePlants);
        cell.consumePlants(plantsToEat);
        increaseSatiation(plantsToEat);
    }
    public abstract Map<Class<? extends Entity>, Double> getFoodProbability();
}
