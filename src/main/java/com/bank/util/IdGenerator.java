package com.bank.util;

public class IdGenerator {

    private static int accountCounter = 1000;

    private IdGenerator() {
    }

    public static String generateAccountNumber() {
        return "ACC" + (++accountCounter);
    }
}