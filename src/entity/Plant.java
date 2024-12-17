package entity;

import islandSimulation.Cell;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Plant extends Entity {
    private String emoji = "🌱";
    private double weight;
    private Cell cell;
}
