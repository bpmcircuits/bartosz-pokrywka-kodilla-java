package com.kodilla.patterns2.observer.homework;

import com.kodilla.patterns2.observer.forum.Observable;
import com.kodilla.patterns2.observer.forum.Observer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HomeworkTopic implements Observable<HomeworkTopic> {

    private final List<Observer<HomeworkTopic>> observers;
    private final Queue<String> exercises;
    private final String name;

    public HomeworkTopic(String name) {
        observers = new ArrayList<>();
        exercises = new LinkedList<>();
        this.name = name;
    }

    public void addExercise(String exercise) {
        exercises.add(exercise);
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer<HomeworkTopic> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<HomeworkTopic> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<HomeworkTopic> observer : observers) {
            observer.update(this);
        }
    }

    public Queue<String> getExercises() {
        return exercises;
    }

    public String getName() {
        return name;
    }
}
