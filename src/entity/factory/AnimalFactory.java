package entity.factory;

import entity.Animal;
import entity.herbivore.*;
import entity.predator.*;
import islandSimulation.Cell;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AnimalFactory {

    public static Animal createAnimal(EntityType type, Cell cell) {
        switch (type) {
            case WOLF:
                return new Wolf(cell);
            case BOA:
                return new Boa(cell);
            case FOX:
                return new Fox(cell);
            case BEAR:
                return new Bear(cell);
            case EAGLE:
                return new Eagle(cell);
            case HORSE:
                return new Horse(cell);
            case DEER:
                return new Deer(cell);
            case RABBIT:
                return new Rabbit(cell);
            case MOUSE:
                return new Mouse(cell);
            case SHEEP:
                return new Sheep(cell);
            case BOAR:
                return new Boar(cell);
            case BUFFALO:
                return new Buffalo(cell);
            case DUCK:
                return new Duck(cell);
            case CATERPILLAR:
                return new Caterpillar(cell);
            default:
                throw new IllegalArgumentException("Неизвестный тип : " + type);
        }
    }
}
