package com.kodilla.patterns2.observer.forum;

import java.util.ArrayList;
import java.util.List;

public class ForumTopic implements Observable<ForumTopic> {
    private final List<Observer<ForumTopic>> observers;
    private final List<String> messages;
    private final String name;

    public ForumTopic(String name) {
        observers = new ArrayList<>();
        messages = new ArrayList<>();
        this.name = name;
    }

    public void addPost(String post) {
        messages.add(post);
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer<ForumTopic> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<ForumTopic> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<ForumTopic> observer : observers) {
            observer.update(this);
        }
    }

    public List<String> getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }
}
