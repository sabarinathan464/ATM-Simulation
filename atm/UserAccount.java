package org.atm;

import java.util.ArrayList;

public class UserAccount {
    private int accountNumber;
    private int pin;
    private double balance;
    private ArrayList<String> transactionHistory;

    // Constructor
    public UserAccount(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    // Getter for account number
    public int getAccountNumber() {
        return accountNumber;
    }

    // Getter for pin
    public int getPin() {
        return pin;
    }

    // Setter for pin
    public void setPin(int pin) {
        this.pin = pin;
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
            System.out.println("Deposited $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money
    public boolean withdraw(double amount, double fee) {
        if (amount > 0 && amount + fee <= balance) {
            balance -= (amount + fee);
            transactionHistory.add("Withdrew: $" + amount + " with fee: $" + fee);
            System.out.println("Withdrew $" + amount + " with fee $" + fee);
            return true;
        } else {
            System.out.println("Insufficient balance or invalid amount.");
            return false;
        }
    }

    // Method to show transaction history
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}
