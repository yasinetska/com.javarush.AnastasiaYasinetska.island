package settings;

import entity.Animal;
import entity.Entity;
import entity.Plant;
import entity.herbivore.*;
import entity.predator.*;
import java.util.Map;

public class FoodProbabilities {

    public static final Map<Class<? extends Animal>, Double> WOLF_PROBABILITIES = Map.of(
            Horse.class, 0.1,
            Deer.class, 0.15,
            Rabbit.class, 0.6,
            Mouse.class, 0.8,
            Sheep.class, 0.7,
            Boar.class, 0.15,
            Buffalo.class, 0.1,
            Duck.class, 0.4
    );

    public static final Map<Class<? extends Animal>, Double> BOA_PROBABILITIES = Map.of(
            Fox.class, 0.15,
            Rabbit.class, 0.2,
            Mouse.class, 0.4,
            Duck.class, 0.1
    );

    public static final Map<Class<? extends Animal>, Double> FOX_PROBABILITIES = Map.of(
            Rabbit.class, 0.7,
            Mouse.class, 0.9,
            Duck.class, 0.6,
            Caterpillar.class, 0.4
    );

    public static final Map<Class<? extends Animal>, Double> BEAR_PROBABILITIES = Map.of(
            Boa.class, 0.8,
            Horse.class, 0.4,
            Deer.class, 0.8,
            Rabbit.class, 0.8,
            Mouse.class, 0.9,
            Sheep.class, 0.7,
            Boar.class, 0.5,
            Buffalo.class, 0.2,
            Duck.class, 0.1
    );

    public static final Map<Class<? extends Animal>, Double> EAGLE_PROBABILITIES = Map.of(
            Fox.class, 0.1,
            Rabbit.class, 0.9,
            Mouse.class, 0.9,
            Duck.class, 0.8
    );

    public static final Map<Class<? extends Entity>, Double> MOUSE_PROBABILITIES = Map.of(
            Caterpillar.class, 0.9,
            Plant.class, 1.00
    );
    public static final Map<Class<? extends Entity>, Double> BOAR_PROBABILITIES = Map.of(
            Mouse.class, 0.5,
            Caterpillar.class, 0.9,
            Plant.class, 1.00
    );
    public static final Map<Class<? extends Entity>, Double> DUCK_PROBABILITIES = Map.of(
            Caterpillar.class, 0.9,
            Plant.class, 1.0
    );
    public static final Map<Class<? extends Entity>, Double> BUFFALO_PROBABILITIES = Map.of(
            Plant.class, 1.0
    );
    public static final Map<Class<? extends Entity>, Double> CATERPILLAR_PROBABILITIES = Map.of(
            Plant.class, 1.0
    );
    public static final Map<Class<? extends Entity>, Double> DEER_PROBABILITIES = Map.of(
            Plant.class, 1.0
    );

    public static final Map<Class<? extends Entity>, Double> HORSE_PROBABILITIES = Map.of(
            Plant.class, 1.0
    );
    public static final Map<Class<? extends Entity>, Double> RABBIT_PROBABILITIES = Map.of(
            Plant.class, 1.0
    );
    public static final Map<Class<? extends Entity>, Double> SHEEP_PROBABILITIES = Map.of(
            Plant.class, 1.0
    );
}
