package islandSimulation;

import entity.Animal;
import entity.Plant;
import lombok.Getter;
import settings.Settings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Cell {
    @Getter
    private final int cellX;
    @Getter
    private final int cellY;
    @Getter
    private final Island island;

    private final CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>();
    private final CopyOnWriteArrayList<Plant> plants = new CopyOnWriteArrayList<>();
    private static final Map<Class<? extends Animal>, Integer> maxAnimalsPerCell = Settings.getMaxAmountCell();

    public Cell(int cellX, int cellY, Island island) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.island = island;
    }

    public int getPlantsCount() {
        return plants.size();
    }

    public void consumePlants(int amount) {
        if (amount <= 0) {
            return;
        }
        int removed = 0;
        for (int i = 0; i < amount && !plants.isEmpty(); i++) {
            plants.remove(0);
            removed++;
        }
    }

    public long getAnimalCount(Class<? extends Animal> animalClass) {
        return animals.stream()
                .filter(animal -> animal.getClass().equals(animalClass))
                .count();
    }

    public void addAnimal(Animal animal) {
        int maxCount = maxAnimalsPerCell.getOrDefault(animal.getClass(), 0);
        if (getAnimalCount(animal.getClass()) < maxCount) {
            animals.add(animal);
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public void growPlants() {
        int currentPlantCount = getPlantsCount();
        int maxPlants = Settings.PLANT_MAX_AMOUNT;
        if (currentPlantCount < maxPlants) {
            int newPlants = ThreadLocalRandom.current().nextInt(maxPlants - currentPlantCount + 1);
            for (int i = 0; i < newPlants; i++) {
                plants.add(new Plant());
            }
        }
    }

    public void addPlant(Plant plant) {
        if (plants.size() < Settings.PLANT_MAX_AMOUNT) {
            plants.add(plant);
        }
    }
}
