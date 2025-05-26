package com.kodilla.patterns2.observer.homework;

import com.kodilla.patterns2.observer.forum.Observer;

public class Mentor implements Observer<HomeworkTopic> {
    private final String username;
    private int updateCount;

    public Mentor(String name) {
        this.username = name;
    }

    @Override
    public void update(HomeworkTopic homeworkTopic) {
        System.out.println(username + ": New student solution in exercise: " + homeworkTopic.getName() + "\n" +
                " (total: " + homeworkTopic.getExercises().size() + " solutions)");
        updateCount++;
    }

    public String getUsername() {
        return username;
    }

    public int getUpdateCount() {
        return updateCount;
    }
}
