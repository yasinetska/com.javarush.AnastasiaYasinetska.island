package entity;

import entity.factory.AnimalFactory;
import entity.factory.EntityType;
import islandSimulation.Cell;
import islandSimulation.Island;
import lombok.Getter;
import lombok.Setter;
import settings.Settings;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Setter
@Getter
public abstract class Animal extends Entity {
    private double maxWeight;
    private int maxAmount;
    private int maxSpeed;
    @Getter
    private String emoji;
    private double maxSatiety;
    private double actualSatiety;
    private boolean isAlive;
    private boolean hasEaten = false;
    private Island island;
    @Getter
    @Setter
    private Cell cell;


    public Animal(Cell cell) {
        this.cell = cell;
        this.maxWeight = Settings.getMaxWeight().get(getClass());
        this.maxAmount = Settings.getMaxAmountCell().get(getClass());
        this.maxSpeed = Settings.getMaxSpeed().get(getClass());
        this.emoji = getEmoji();
        this.maxSatiety = Settings.getFoodForMaxSatiety().get(getClass());
        this.actualSatiety = 1.00;
        this.isAlive = true;
    }

    public abstract void eat(Cell cell);

    public void markAsEaten() {
        this.hasEaten = true;
    }

    public void loseEnergy() {
        actualSatiety -= 1.0; // Потеря энергии
        if (actualSatiety <= 0) {
            die();
        }
    }

    public void increaseSatiation(double foodAmount) {
        actualSatiety = Math.min(getMaxSatiety(), actualSatiety + foodAmount);
        markAsEaten();
    }

    public boolean isAlive() {
        return actualSatiety > 0;
    }

    public void die() {
        if (this.cell != null) {
            this.cell.removeAnimal(this);
            this.isAlive = false;
        } else {
            System.err.println("Ошибка: cell == null при вызове die().");
        }
    }

    public void reproduce(Cell currentCell) {
        if (!hasEaten) {
            return;
        }
        List<Animal> animals = currentCell.getAnimals();
        long sameEntityCount = animals.stream().filter(a -> a.getClass() == this.getClass()).count();
        int maxAnimalsOfType = Settings.getMaxAmountCell().get(this.getClass());

        if (sameEntityCount >= 2 && currentCell.getAnimalCount(this.getClass()) < maxAnimalsOfType) {
            Animal newEntity = AnimalFactory.createAnimal(EntityType.getByItemClass(this.getClass()), currentCell);
            currentCell.addAnimal(newEntity);
        }
        this.hasEaten = false;
    }

    public List<int[]> chooseDirection(int x, int y) {
        List<int[]> availableMoves = new ArrayList<>();

        for (int dx = -maxSpeed; dx <= maxSpeed; dx++) {
            for (int dy = -maxSpeed; dy <= maxSpeed; dy++) {
                if (dx == 0 && dy == 0) continue; // Игнорируем текущую клетку
                int newX = x + dx;
                int newY = y + dy;

                if (newX >= 0 && newX < Island.getRows() && newY >= 0 && newY < Island.getColumns()) {
                    availableMoves.add(new int[]{newX, newY});
                }
            }
        }
        return availableMoves;
    }

    public void move(Cell currentCell) {

        List<int[]> availableMoves = chooseDirection(currentCell.getCellX(), currentCell.getCellY());

        if (!availableMoves.isEmpty()) {

            int[] newCoords = availableMoves.get(ThreadLocalRandom.current().nextInt(availableMoves.size()));
            currentCell.removeAnimal(this);

            int newX = newCoords[0];
            int newY = newCoords[1];
            Cell newCell = this.cell.getIsland().getCell(newX, newY); // Используем текущий остров
            this.setCell(newCell);
            newCell.addAnimal(this);
        }
    }
}
