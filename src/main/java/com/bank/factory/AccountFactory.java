package com.bank.factory;

import com.bank.model.Account;
import com.bank.model.CurrentAccount;
import com.bank.model.SavingsAccount;
import com.bank.util.IdGenerator;

public class AccountFactory {

    private AccountFactory() {
    }

    public static Account createAccount(String type, String holderName, double initialBalance) {

        String accountNumber = IdGenerator.generateAccountNumber();

        if (type.equalsIgnoreCase("Savings")) {
            return new SavingsAccount(accountNumber, holderName, initialBalance);
        }

        if (type.equalsIgnoreCase("Current")) {
            return new CurrentAccount(accountNumber, holderName, initialBalance);
        }

        throw new IllegalArgumentException("Invalid account type.");
    }
}