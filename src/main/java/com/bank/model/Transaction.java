package com.bank.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(TransactionType type, double amount) {
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}