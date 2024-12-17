package entity.factory;

import entity.Entity;
import entity.Plant;
import entity.herbivore.*;
import entity.predator.*;
import lombok.Getter;

@Getter
public enum EntityType {

    WOLF("🐺", Wolf.class),
    BOA("🐍", Boa.class),
    FOX("🦊", Fox.class),
    BEAR("🐻", Bear.class),
    EAGLE("🦅", Eagle.class),
    HORSE("🐎", Horse.class),
    DEER("🦌", Deer.class),
    RABBIT("🐇", Rabbit.class),
    MOUSE("🐁", Mouse.class),
    SHEEP("🐑", Sheep.class),
    BOAR("🐗", Boar.class),
    BUFFALO("🐃", Buffalo.class),
    DUCK("🦆", Duck.class),
    CATERPILLAR("🐛", Caterpillar.class),
    PLANT("🌿", Plant.class);

    @Getter
    private final String emoji;
    @Getter
    private Class<? extends Entity> itemClass;

    EntityType(String emoji, Class<? extends Entity> itemClass) {
        this.emoji = emoji;
        this.itemClass = itemClass;
    }

    public static EntityType getByItemClass(Class<? extends Entity> itemClass) {
        for (EntityType entityType : EntityType.values()) {
            if (entityType.getItemClass() != null && entityType.getItemClass().equals(itemClass)) {
                return entityType;
            }
        }
        return null;
    }
}
