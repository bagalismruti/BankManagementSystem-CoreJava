package com.bank;

import com.bank.manager.BankManager;
import com.bank.model.Account;
import com.bank.observer.ConsoleObserver;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankManager manager = BankManager.getInstance();

        manager.addObserver(new ConsoleObserver());

        while (true) {

            System.out.println("\n========================================");
            System.out.println("        BANK MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Open New Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Funds");
            System.out.println("5. View Mini Statement");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Close Account");
            System.out.println("8. Apply Monthly Interest/Fee");
            System.out.println("9. Exit");
            System.out.println("========================================");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.println("\n------ Open New Account ------");

                    System.out.print("Enter Account Type (Savings/Current): ");
                    String type = sc.nextLine();

                    System.out.print("Enter Holder Name: ");
                    String holderName = sc.nextLine();

                    System.out.print("Enter Initial Deposit: ");
                    double deposit = sc.nextDouble();

                    try {

                        Account account = manager.createAccount(
                                type,
                                holderName,
                                deposit
                        );

                        System.out.println("\nAccount Created Successfully!");
                        System.out.println(account);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:

                    System.out.println("\n------ Deposit ------");

                    System.out.print("Enter Account Number : ");
                    String depositAcc = sc.nextLine();

                    System.out.print("Enter Amount : ");
                    double depAmount = sc.nextDouble();

                    try {

                        manager.deposit(depositAcc, depAmount);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:

                    System.out.println("\n------ Withdraw ------");

                    System.out.print("Enter Account Number : ");
                    String withdrawAcc = sc.nextLine();

                    System.out.print("Enter Amount : ");
                    double withdrawAmount = sc.nextDouble();

                    try {

                        manager.withdraw(withdrawAcc, withdrawAmount);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:

                    System.out.println("\n------ Transfer ------");

                    System.out.print("From Account : ");
                    String from = sc.nextLine();

                    System.out.print("To Account : ");
                    String to = sc.nextLine();

                    System.out.print("Amount : ");
                    double amount = sc.nextDouble();

                    try {

                        manager.transfer(from, to, amount);

                    } catch (Exception e) {

                        System.out.println(e.getMessage());
                    }

                    break;

                case 5:

                    System.out.println("\n------ Mini Statement ------");

                    System.out.print("Enter Account Number : ");

                    String statementAcc = sc.nextLine();

                    manager.printMiniStatement(statementAcc);

                    break;
                case 6:

                    System.out.println("\n------ All Accounts ------");

                    manager.displayAllAccounts();

                    break;

                case 7:

                    System.out.println("\n------ Close Account ------");

                    System.out.print("Enter Account Number : ");

                    String closeAcc = sc.nextLine();

                    if (manager.deleteAccount(closeAcc)) {

                        System.out.println("Account Closed Successfully.");

                    } else {

                        System.out.println("Account Not Found.");
                    }

                    break;

                case 8:

                    System.out.println("\nApplying Monthly Interest/Fee...");

                    manager.applyMonthlyInterestOrFee();

                    break;

                case 9:

                    System.out.println("\n========================================");
                    System.out.println(" Thank You For Using");
                    System.out.println(" BANK MANAGEMENT SYSTEM");
                    System.out.println("========================================");

                    sc.close();
                    System.exit(0);
                    break;

                default:

                    System.out.println("Invalid Choice! Please Try Again.");
            }
        }
    }
}