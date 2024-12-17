import islandSimulation.IslandSimulation;

public class App {
    public static void main(String[] args) {

        IslandSimulation simulation = new IslandSimulation();

        simulation.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        simulation.stop();
    }
}
