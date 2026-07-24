package com.bank.model;

public class SavingsAccount extends Account {

    private static final double INTEREST_RATE = 0.04;

    public SavingsAccount(String accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid withdrawal amount.");
        }

        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        balance -= amount;

        getTransactionHistory().add(
                new Transaction(TransactionType.WITHDRAW, amount)
        );
    }

    @Override
    public void applyMonthlyInterestOrFee() {
        balance += balance * INTEREST_RATE;
    }

    @Override
    public String toString() {
        return "Savings Account\n" + super.toString();
    }
}