package islandSimulation;

import entity.Animal;
import entity.Plant;
import entity.factory.AnimalFactory;
import entity.factory.EntityType;
import lombok.Getter;
import settings.Settings;
import java.util.concurrent.ThreadLocalRandom;

public class Island {
    @Getter
    private final Cell[][] cells;
    @Getter
    private static final int rows = Settings.ISLAND_ROWS;
    @Getter
    private static final int columns = Settings.ISLAND_COLUMNS;

    public Island() {
        this.cells = new Cell[rows][columns];
        initializeCells();
        populateCellsWithPlants();
        populateCellsWithAnimals();
    }

    private void initializeCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new Cell(i, j, this);
            }
        }
    }
    private void populateCellsWithPlants() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = cells[i][j];
                int maxPlants = Settings.PLANT_MAX_AMOUNT;
                for (int k = 0; k < maxPlants; k++) {
                    cell.addPlant(new Plant());
                }
            }
        }
    }
    private void populateCellsWithAnimals() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = cells[i][j];

                for (EntityType entityType : EntityType.values()) {
                    if (Animal.class.isAssignableFrom(entityType.getItemClass())) {
                        int maxCount = Settings.getMaxAnimalsPerCell((Class<? extends Animal>) entityType.getItemClass());

                        int count = ThreadLocalRandom.current().nextInt(maxCount + 1);

                        for (int k = 0; k < count; k++) {
                            try {
                                Animal animal = AnimalFactory.createAnimal(entityType, cell);
                                cell.addAnimal(animal);
                            } catch (Exception e) {
                                System.err.println("Ошибка создания животного: " + e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    public Cell getCell(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) {
            throw new IndexOutOfBoundsException("Неверные координаты клетки: " + row + ", " + column);
        }
        return cells[row][column];
    }
}
