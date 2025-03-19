package com.kodilla.spring.portfolio;

public class Board {

    private final TaskList toDoList;
    private final TaskList inProgressList;
    private final TaskList doneList;

    public Board() {
        this.toDoList = new TaskList();
        this.inProgressList = new TaskList();
        this.doneList = new TaskList();
    }

    public TaskList getToDoList() {
        return toDoList;
    }

    public TaskList getInProgressList() {
        return inProgressList;
    }

    public TaskList getDoneList() {
        return doneList;
    }
}
