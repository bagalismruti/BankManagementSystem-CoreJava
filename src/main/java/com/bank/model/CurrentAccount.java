package com.bank.model;

public class CurrentAccount extends Account {

    private static final double MONTHLY_FEE = 100;

    public CurrentAccount(String accountNumber, String holderName, double balance) {
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

        if (balance >= MONTHLY_FEE) {
            balance -= MONTHLY_FEE;
        }
    }

    @Override
    public String toString() {
        return "Current Account\n" + super.toString();
    }
}