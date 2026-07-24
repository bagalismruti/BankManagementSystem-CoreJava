package com.bank.observer;

public class ConsoleObserver implements AccountObserver {

    @Override
    public void update(String message) {

        System.out.println("\n===============================");
        System.out.println(" BANK NOTIFICATION");
        System.out.println("===============================");
        System.out.println(message);
        System.out.println("===============================\n");
    }
}