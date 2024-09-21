package org.atm;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private UserAccount currentUser;
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private int failedAttempts = 0;
    private boolean isLocked = false;
    private static final double WITHDRAWAL_FEE = 2.50;

    // Login method to authenticate the user
    public boolean login(ArrayList<UserAccount> accounts, int accountNumber, int pin) {
        if (isLocked) {
            System.out.println("Account is locked due to multiple failed login attempts.");
            return false;
        }

        for (UserAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getPin() == pin) {
                currentUser = account;
                failedAttempts = 0; // Reset on successful login
                return true;
            }
        }

        failedAttempts++;
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            isLocked = true;
            System.out.println("Account locked due to multiple failed login attempts.");
        }
        return false;
    }

    // Method to display ATM menu options
    public void showMenu() {
        System.out.println("\nWelcome to the ATM!");
        System.out.println("1. View Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. View Transaction History");
        System.out.println("5. Change PIN");
        System.out.println("6. Exit");
    }

    // Method to handle operations based on user choice
    public void performOperations() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            showMenu();
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Current Balance: $" + currentUser.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    currentUser.withdraw(withdrawAmount, WITHDRAWAL_FEE);
                    break;
                case 4:
                    currentUser.showTransactionHistory();
                    break;
                case 5:
                    System.out.print("Enter old PIN: ");
                    int oldPin = scanner.nextInt();
                    System.out.print("Enter new PIN: ");
                    int newPin = scanner.nextInt();
                    if (currentUser.getPin() == oldPin) {
                        currentUser.setPin(newPin);
                        System.out.println("PIN changed successfully.");
                    } else {
                        System.out.println("Old PIN is incorrect.");
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 6);
    }
}
