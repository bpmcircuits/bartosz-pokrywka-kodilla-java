package com.kodilla.patterns.factory.tasks;

public class DrivingTask implements Task {

    private final String taskName;
    private final String where;
    private final String using;

    public DrivingTask(String taskName, String where, String using) {
        this.taskName = taskName;
        this.where = where;
        this.using = using;
    }

    @Override
    public void executeTask() {
        System.out.println("Executing driving task: " + taskName);
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public boolean isTaskExecuted() {
        return using.equals("Nissan");
    }
}
