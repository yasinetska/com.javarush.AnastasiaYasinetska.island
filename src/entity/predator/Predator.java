package entity.predator;

import entity.Animal;
import islandSimulation.Cell;
import java.util.List;
import java.util.Map;

public abstract class Predator extends Animal {
    public Predator(Cell cell) {
        super(cell);
    }

    @Override
    public void eat(Cell cell) {
        List<Animal> potentialPrey = cell.getAnimals().stream()
                .filter(prey -> canEat(prey) && prey.isAlive())
                .toList();

        if (potentialPrey.isEmpty()) {
            loseEnergy();
            return;
        }

        for (Animal prey : potentialPrey) {
            double chance = getHuntingChance(prey);
            if (Math.random() * 100 < chance) {
                double foodEaten = Math.min(prey.getMaxWeight(), getMaxSatiety() - getActualSatiety());

                prey.die();
                increaseSatiation(foodEaten);
                return;
            }
        }
        loseEnergy();
    }

    public boolean canEat(Animal prey) {
        if (prey == null) {
            return false;
        }
        return getFoodProbability().containsKey(prey.getClass());
    }

    public double getHuntingChance(Animal prey) {
        if (prey == null) {
            return 0.0;
        }
        return getFoodProbability().getOrDefault(prey.getClass(), 0.0);
    }

    public abstract Map<Class<? extends Animal>, Double> getFoodProbability();
}
