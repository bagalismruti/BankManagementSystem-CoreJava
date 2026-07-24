package com.bank.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String accountNumber;
    private String holderName;
    protected double balance;
    private List<Transaction> transactionHistory;

    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        balance += amount;
        transactionHistory.add(new Transaction(TransactionType.DEPOSIT, amount));
    }

    public abstract void withdraw(double amount);

    public abstract void applyMonthlyInterestOrFee();

    @Override
    public String toString() {
        return "----------------------------------\n" +
                "Account Number : " + accountNumber +
                "\nHolder Name   : " + holderName +
                "\nBalance       : ₹" + balance;
    }
}