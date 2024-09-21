package org.atm;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create an ArrayList to store UserAccount objects
        ArrayList<UserAccount> accounts = new ArrayList<>();

        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM System");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Create a New Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Login process
                    boolean loggedIn = false;
                    while (!loggedIn) {
                        System.out.print("Enter Account Number: ");
                        int accountNumber = scanner.nextInt();
                        System.out.print("Enter PIN: ");
                        int pin = scanner.nextInt();

                        loggedIn = atm.login(accounts, accountNumber, pin);

                        if (!loggedIn) {
                            System.out.println("Invalid account number or PIN. Try again.");
                        }
                    }

                    // Perform ATM operations once logged in
                    atm.performOperations();
                    break;

                case 2:
                    // Account creation process
                    System.out.print("Enter a new Account Number: ");
                    int newAccountNumber = scanner.nextInt();

                    // Check if account number already exists
                    boolean accountExists = false;
                    for (UserAccount account : accounts) {
                        if (account.getAccountNumber() == newAccountNumber) {
                            accountExists = true;
                            break;
                        }
                    }

                    if (accountExists) {
                        System.out.println("Account number already exists. Please choose a different one.");
                    } else {
                        System.out.print("Enter a new PIN: ");
                        int newPin = scanner.nextInt();
                        System.out.print("Enter the initial deposit: ");
                        double initialDeposit = scanner.nextDouble();

                        // Create new account and add to the list
                        UserAccount newUser = new UserAccount(newAccountNumber, newPin, initialDeposit);
                        accounts.add(newUser);
                        System.out.println("Account created successfully!");
                    }
                    break;

                case 3:
                    // Exit the program
                    System.out.println("Thank you for using the ATM System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }
}
