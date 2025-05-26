package com.kodilla.patterns2.observer.forum;

public interface Observable<T> {
    void registerObserver(Observer<T> observer);
    void removeObserver(Observer<T> observer);
    void notifyObservers();
}
