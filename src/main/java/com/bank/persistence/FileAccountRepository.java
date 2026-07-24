package com.bank.persistence;

import com.bank.model.Account;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

public class FileAccountRepository implements AccountRepository {

    private static final String FILE_NAME = "accounts.dat";

    @Override
    public void saveAccounts(Collection<Account> accounts) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(new ArrayList<>(accounts));

            System.out.println("Accounts saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving accounts.");
            e.printStackTrace();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Account> loadAccounts() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (Collection<Account>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error loading accounts.");
            return new ArrayList<>();
        }
    }
}