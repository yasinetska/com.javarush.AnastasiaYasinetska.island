package islandSimulation;

import entity.Animal;
import entity.factory.EntityType;
import lombok.Getter;
import lombok.Setter;
import settings.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Setter
@Getter
public class IslandSimulation {

    private final int width = Island.getRows();
    private final int height = Island.getColumns();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private final ExecutorService animalExecutor = Executors.newFixedThreadPool(Settings.THREAD_POOL_SIZE);
    private final int dayDuration = Settings.DAY_DURATION;
    private int dayCounter = 0;
    private Island island;
    private boolean isInitialStatisticsCollected = false;
    private int initialTotalPlants = 0;
    private Map<Class<? extends Animal>, Integer> initialAnimalCounts = new HashMap<>();
    private int initialAnimalsCount = 0;


    public void start() {
        island = new Island();
        System.out.println("СОЗДАН ОСТРОВ");

        scheduler.scheduleAtFixedRate(() -> growPlants(), 0, dayDuration, TimeUnit.MILLISECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            simulateStep();
            printStatistics();
        }, 0, dayDuration, TimeUnit.MILLISECONDS);
    }

    private void growPlants() {
        Arrays.stream(island.getCells())
                .flatMap(Arrays::stream)
                .forEach(Cell::growPlants);
    }

    private void simulateStep() {
        CountDownLatch latch = new CountDownLatch(width * height);

        Arrays.stream(island.getCells())
                .flatMap(Arrays::stream)
                .forEach(cell -> animalExecutor.execute(() -> {
                    try {
                        for (Animal animal : new ArrayList<>(cell.getAnimals())) {
                            if (animal.isAlive()) {
                                animal.eat(cell);
                                animal.reproduce(cell);
                                animal.move(cell);
                            }
                        }
                    } finally {
                        latch.countDown();
                    }
                }));

        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Симуляция была прервана.");
        }
    }

    public void stop() {
        scheduler.shutdown();
        animalExecutor.shutdown();
        try {
            if (!scheduler.awaitTermination(1, TimeUnit.MINUTES)) {
                scheduler.shutdownNow();
            }
            if (!animalExecutor.awaitTermination(5, TimeUnit.MINUTES)) {
                animalExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            animalExecutor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private void printStatistics() {
        int totalPlants = 0;
        Map<Class<? extends Animal>, Integer> aliveAnimalCounts = new HashMap<>();
        int aliveAnimals = 0;
        int animalsAtStartOfDay = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = island.getCell(i, j);
                animalsAtStartOfDay += cell.getAnimals().size();
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Cell cell = island.getCell(i, j);

                totalPlants += cell.getPlants().size();

                for (Animal animal : cell.getAnimals()) {
                    if (animal.isAlive()) {
                        aliveAnimals++;
                        aliveAnimalCounts.put(
                                animal.getClass(),
                                aliveAnimalCounts.getOrDefault(animal.getClass(), 0) + 1
                        );
                    }
                }

                if (!isInitialStatisticsCollected) {
                    for (Animal animal : cell.getAnimals()) {
                        initialAnimalCounts.put(
                                animal.getClass(),
                                initialAnimalCounts.getOrDefault(animal.getClass(), 0) + 1
                        );
                        initialAnimalsCount++;
                    }
                    initialTotalPlants += cell.getPlants().size();
                }
            }
        }

        if (!isInitialStatisticsCollected) {
            isInitialStatisticsCollected = true;
        }

        int deadAnimals = animalsAtStartOfDay - aliveAnimals;

        System.out.println("\n========== ОСТРОВ ==========");
        System.out.println("====== СТАТИСТИКА ЗА ДЕНЬ " + dayCounter++ + " ======");

        if (dayCounter == 1) {
            System.out.println("Начальная статистика:");
            System.out.printf("Всего создано животных: %d\n", initialAnimalsCount);
            System.out.printf("Всего создано растений: %d\n", initialTotalPlants);

            System.out.println("Животные по классам (изначально):");
            for (Map.Entry<Class<? extends Animal>, Integer> entry : initialAnimalCounts.entrySet()) {
                EntityType entityType = EntityType.getByItemClass(entry.getKey());
                String emoji = entityType != null ? entityType.getEmoji() : "❓";
                System.out.printf("%s %s: %d\n", emoji, entry.getKey().getSimpleName(), entry.getValue());
            }
            System.out.println();
        }

        if(dayCounter > 1) {
            System.out.println("Живые животные по классам:");
            for (Map.Entry<Class<? extends Animal>, Integer> entry : aliveAnimalCounts.entrySet()) {
                EntityType entityType = EntityType.getByItemClass(entry.getKey());
                String emoji = entityType != null ? entityType.getEmoji() : "❓";
                System.out.printf("%s %s: %d\n", emoji, entry.getKey().getSimpleName(), entry.getValue());
            }

            System.out.println("\nОбщая статистика:");
            System.out.printf("Всего живых животных: %d\n", aliveAnimals);
            System.out.printf("Всего умерших животных: %d\n", deadAnimals);
            System.out.printf("\uD83C\uDF3F Осталось растений: %d\n", totalPlants);
            System.out.println("========================");
        }

    }
}
