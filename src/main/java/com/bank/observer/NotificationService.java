package com.bank.observer;

import java.util.ArrayList;
import java.util.List;

public class NotificationService implements Subject {

    private final List<AccountObserver> observers;

    public NotificationService() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(AccountObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(AccountObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {

        for (AccountObserver observer : observers) {
            observer.update(message);
        }
    }
}