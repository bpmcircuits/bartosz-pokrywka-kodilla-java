package com.kodilla.patterns.factory.tasks;

public class TaskFactory {

    public static final String DRIVING_TASK = "DRIVING_TASK";
    public static final String PAINTING_TASK = "PAINTING_TASK";
    public static final String SHOPPING_TASK = "SHOPPING_TASK";

    public static Task createTask(final String taskName) {
        return switch (taskName) {
            case DRIVING_TASK -> new DrivingTask("Drive to school", "School", "Nissan");
            case PAINTING_TASK -> new PaintingTask("Painting walls", "white", "wall");
            case SHOPPING_TASK -> new ShoppingTask("Buying groceries", "Apples", 10.5);
            default -> null;
        };
    }

}
