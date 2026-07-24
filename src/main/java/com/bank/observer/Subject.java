package com.bank.observer;

public interface Subject {

    void addObserver(AccountObserver observer);

    void removeObserver(AccountObserver observer);

    void notifyObservers(String message);
}