package settings;

import entity.Animal;
import entity.Entity;
import entity.Plant;
import entity.herbivore.*;
import entity.predator.*;
import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Settings {

    public static final int ISLAND_ROWS = 10;
    public static final int ISLAND_COLUMNS = 10;
    public static final int THREAD_POOL_SIZE = 4;
    public static final int DAY_DURATION = 100; // Длительность одного дня на острове в миллисекундах
    public static final int SIMULATION_DAYS = 50;
    public static final double PLANT_MAX_WEIGHT = 1.0;
    public static final int PLANT_MAX_AMOUNT = 200;

    public static Map<Class<? extends Entity>, Double> getMaxWeight(){
        Map<Class<? extends Entity>, Double> maxWeight = new HashMap<>();
        maxWeight.put(Wolf.class, 50.0);
        maxWeight.put(Bear.class, 500.0);
        maxWeight.put(Boa.class, 15.0);
        maxWeight.put(Eagle.class, 6.0);
        maxWeight.put(Fox.class, 8.0);
        maxWeight.put(Rabbit.class, 2.0);
        maxWeight.put(Duck.class, 1.0);
        maxWeight.put(Boar.class, 400.0);
        maxWeight.put(Buffalo.class, 700.0);
        maxWeight.put(Caterpillar.class, 0.01);
        maxWeight.put(Deer.class, 300.0);
        maxWeight.put(Horse.class, 400.0);
        maxWeight.put(Mouse.class, 0.05);
        maxWeight.put(Sheep.class, 70.0);
        maxWeight.put(Plant.class, 1.0);
        return maxWeight;
    }

    public static int getMaxAnimalsPerCell(Class<? extends Animal> animalClass) {
        return getMaxAmountCell().getOrDefault(animalClass, 0);
    }

    public static final Map<Class<? extends Animal>, Integer> getMaxAmountCell(){
        Map<Class<? extends Animal>, Integer> maxAmountCell = new HashMap<>();
        maxAmountCell.put(Wolf.class, 30);
        maxAmountCell.put(Bear.class, 5);
        maxAmountCell.put(Boa.class, 30);
        maxAmountCell.put(Eagle.class, 20);
        maxAmountCell.put(Fox.class, 30);
        maxAmountCell.put(Rabbit.class, 150);
        maxAmountCell.put(Duck.class, 200);
        maxAmountCell.put(Boar.class, 50);
        maxAmountCell.put(Buffalo.class, 10);
        maxAmountCell.put(Caterpillar.class, 1000);
        maxAmountCell.put(Deer.class, 140);
        maxAmountCell.put(Horse.class, 20);
        maxAmountCell.put(Mouse.class, 500);
        maxAmountCell.put(Sheep.class, 140);

        return maxAmountCell;
    }

    public static Map<Class<? extends Animal>, Integer> getMaxSpeed(){
        Map<Class<? extends Animal>, Integer> maxSpeed= new HashMap<>();
        maxSpeed.put(Wolf.class, 3);
        maxSpeed.put(Bear.class, 2);
        maxSpeed.put(Boa.class, 1);
        maxSpeed.put(Eagle.class, 3);
        maxSpeed.put(Fox.class, 2);
        maxSpeed.put(Rabbit.class, 2);
        maxSpeed.put(Duck.class, 4);
        maxSpeed.put(Boar.class, 2);
        maxSpeed.put(Buffalo.class, 3);
        maxSpeed.put(Caterpillar.class, 0);
        maxSpeed.put(Deer.class, 3);
        maxSpeed.put(Horse.class, 4);
        maxSpeed.put(Mouse.class, 1);
        maxSpeed.put(Sheep.class, 3);
        return maxSpeed;
    }

    public static Map<Class<? extends Animal>, Double> getFoodForMaxSatiety(){
        Map<Class<? extends Animal>, Double> maxSatiety = new HashMap<>();
        maxSatiety.put(Wolf.class, 8.0);
        maxSatiety.put(Bear.class, 80.0);
        maxSatiety.put(Boa.class, 3.0);
        maxSatiety.put(Eagle.class, 1.0);
        maxSatiety.put(Fox.class, 2.0);
        maxSatiety.put(Rabbit.class, 0.45);
        maxSatiety.put(Duck.class, 0.15);
        maxSatiety.put(Boar.class, 50.0);
        maxSatiety.put(Buffalo.class, 100.0);
        maxSatiety.put(Caterpillar.class, 0.0);
        maxSatiety.put(Deer.class, 50.0);
        maxSatiety.put(Horse.class, 60.0);
        maxSatiety.put(Mouse.class, 0.01);
        maxSatiety.put(Sheep.class, 15.0);

        return maxSatiety;
    }
}

