package com.bank.manager;

import com.bank.factory.AccountFactory;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.observer.AccountObserver;
import com.bank.observer.Subject;
import com.bank.persistence.AccountRepository;
import com.bank.persistence.FileAccountRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankManager implements Subject {

    private static BankManager instance;

    private final Map<String, Account> accounts;
    private final List<AccountObserver> observers;
    private final AccountRepository repository;

    private BankManager() {

        accounts = new HashMap<>();
        observers = new ArrayList<>();
        repository = new FileAccountRepository();

        loadAccounts();
    }

    public static BankManager getInstance() {

        if (instance == null) {
            instance = new BankManager();
        }

        return instance;
    }

    // -----------------------------
    // Persistence
    // -----------------------------

    private void loadAccounts() {

        Collection<Account> savedAccounts = repository.loadAccounts();

        for (Account account : savedAccounts) {
            accounts.put(account.getAccountNumber(), account);
        }
    }

    private void saveAccounts() {
        repository.saveAccounts(accounts.values());
    }

    // -----------------------------
    // Account Operations
    // -----------------------------

    public Account createAccount(String type,
                                 String holderName,
                                 double initialBalance) {

        Account account =
                AccountFactory.createAccount(type, holderName, initialBalance);

        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("Duplicate Account.");
        }

        accounts.put(account.getAccountNumber(), account);

        saveAccounts();

        notifyObservers(
                "Account Created : "
                        + account.getAccountNumber()
                        + " ("
                        + holderName
                        + ")"
        );

        return account;
    }

    public Account findAccount(String accountNumber) {

        return accounts.get(accountNumber);
    }

    public void deposit(String accountNumber,
                        double amount) {

        Account account = findAccount(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        account.deposit(amount);

        saveAccounts();

        notifyObservers(
                "Deposit Successful\n"
                        + "Account : "
                        + account.getAccountNumber()
                        + "\nAmount  : ₹"
                        + amount
                        + "\nBalance : ₹"
                        + account.getBalance()
        );
    }

    public void withdraw(String accountNumber,
                         double amount) {

        Account account = findAccount(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found.");
        }

        account.withdraw(amount);

        saveAccounts();

        notifyObservers(
                "Withdrawal Successful\n"
                        + "Account : "
                        + account.getAccountNumber()
                        + "\nAmount  : ₹"
                        + amount
                        + "\nBalance : ₹"
                        + account.getBalance()
        );
    }
    public void transfer(String fromAccount,
                         String toAccount,
                         double amount) {

        Account sender = findAccount(fromAccount);
        Account receiver = findAccount(toAccount);

        if (sender == null) {
            throw new IllegalArgumentException("Sender account not found.");
        }

        if (receiver == null) {
            throw new IllegalArgumentException("Receiver account not found.");
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        saveAccounts();

        notifyObservers(
                "Transfer Successful\n"
                        + "From    : " + sender.getAccountNumber()
                        + "\nTo      : " + receiver.getAccountNumber()
                        + "\nAmount  : ₹" + amount
        );
    }

    public boolean deleteAccount(String accountNumber) {

        if (!accounts.containsKey(accountNumber)) {
            return false;
        }

        accounts.remove(accountNumber);

        saveAccounts();

        notifyObservers(
                "Account Closed : " + accountNumber
        );

        return true;
    }

    public void displayAccount(String accountNumber) {

        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println(account);
    }

    public void displayAllAccounts() {

        if (accounts.isEmpty()) {

            System.out.println("No Accounts Available.");
            return;
        }

        System.out.println("\n========== ALL ACCOUNTS ==========");

        for (Account account : accounts.values()) {

            System.out.println(account);
            System.out.println("----------------------------------");
        }
    }

    public void printMiniStatement(String accountNumber) {

        Account account = findAccount(accountNumber);

        if (account == null) {

            System.out.println("Account not found.");
            return;
        }

        System.out.println("\n========== MINI STATEMENT ==========");
        System.out.println(account);

        if (account.getTransactionHistory().isEmpty()) {

            System.out.println("No Transactions Found.");
            return;
        }

        for (Transaction transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }

    public void applyMonthlyInterestOrFee() {

        for (Account account : accounts.values()) {
            account.applyMonthlyInterestOrFee();
        }

        saveAccounts();

        notifyObservers("Monthly Interest/Fee Applied Successfully.");
    }

    public int getTotalAccounts() {
        return accounts.size();
    }

    public Collection<Account> getAccounts() {
        return accounts.values();
    }

    // ==========================================
    // Observer Pattern Methods
    // ==========================================

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