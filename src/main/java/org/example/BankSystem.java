package org.example;

import java.util.*;

public class BankSystem {

    public static BankAccount findBankAccount(ArrayList<BankAccount> accounts ,int accountNumber) {
        for (BankAccount ba : accounts) {
            if(accountNumber == ba.getAccountNumber()) {
                return ba;
            }
        }

        return null;
    }

    public static boolean hasSameAccountNumber(ArrayList<BankAccount> accounts ,int accountNumber) {
        for (BankAccount ba : accounts) {
            if(accountNumber == ba.getAccountNumber()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // Store multiple accounts
        ArrayList<BankAccount> accounts = new ArrayList<>();

        // add existing accounts
        accounts.add(new BankAccount(123, "Test Person"));

        boolean keepTransacting = true;
        int option;

        while (keepTransacting) {
            System.out.println("=== Bank Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Check Balance");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");

            System.out.print("Enter choice(1-5): ");
            // get user input
            option = sc.nextInt();
            // prevent skipping the next input
            sc.nextLine();

            switch(option) {
                // Add new account
                case 1:
                {
                    System.out.print("Enter Account Number: ");
                    int accountNumber = sc.nextInt();
                    sc.nextLine();
                    // validate accountnumber
                    while (hasSameAccountNumber(accounts, accountNumber)){
                        System.out.println("Account number already exists." );
                        System.out.print("Enter Account Number: ");
                        accountNumber = sc.nextInt();
                        sc.nextLine();
                    }


                    System.out.print("Enter Holder Name: ");
                    // Get account name
                    String name = sc.nextLine();
                    // Create bank account object
                    BankAccount ba = new BankAccount(accountNumber, name);
                    //ask if user wants to deposit
                    System.out.print("Initial deposit? (yes/no): ");
                    String ans = sc.nextLine();
                    if (ans.equalsIgnoreCase("yes")){
                        System.out.print("Enter initial deposit amount: ");
                        double amount = sc.nextDouble();
                        sc.nextLine();
                        // input validation
                        while(amount <=0) {
                            System.out.println("Amount must be greater than 0" );
                            System.out.print("Enter initial deposit amount: ");
                            amount = sc.nextDouble();
                            sc.nextLine();
                        }
                        // update balance
                        ba.deposit(amount);
                    }
                    System.out.println("Account created successfully!");
                    // add to list
                    accounts.add(ba);
                    break;
                }
                //View all accounts
                case 2:
                {
                    System.out.println("--- Available Bank Accounts ---");
                    for (BankAccount ba : accounts)
                        // print account data
                        ba.displayInformation();
                    break;
                }
                // Check balance
                case 3:
                {
                    System.out.print("Enter account number: ");
                    // get and check account number
                    int accountNumber = sc.nextInt();
                    sc.nextLine();
                    boolean isFound = false;
                    for (BankAccount ba : accounts) {
                        if(accountNumber == ba.getAccountNumber()) {
                            System.out.printf("Available Balance: %.2f", ba.getAvailableBalance());
                            isFound = true;
                            break;
                        }
                    }
                    // if account number doesn't exist
                    if (!isFound)
                        System.out.printf("Bank Account Number %d doesn't exist", accountNumber);
                    break;
                }
                //Deposit
                case 4:
                {
                    System.out.print("Enter account number: ");
                    int accountNumber = sc.nextInt();
                    sc.nextLine();
                    // check if there is an existing account
                    BankAccount existingBankAccount = findBankAccount(accounts, accountNumber);
                    while (existingBankAccount == null) {
                        System.out.printf("Bank Account Number %d doesn't exist", accountNumber);
                        System.out.println();
                        System.out.print("Enter account number: ");
                        accountNumber = sc.nextInt();
                        sc.nextLine();
                        existingBankAccount = findBankAccount(accounts, accountNumber);
                    }
                    System.out.print("Enter deposit amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();
                    // amount validation
                    while(amount <=0) {
                        System.out.println("Amount must be greater than 0" );
                        System.out.print("Enter deposit amount: ");
                        amount = sc.nextDouble();
                        sc.nextLine();
                    }
                    // update balance
                    existingBankAccount.deposit(amount);
                    System.out.printf("Your current balance now is %.2f", existingBankAccount.getAvailableBalance());
                    break;
                }
                //Withdrawal
                case 5:
                {
                    System.out.print("Enter account number: ");
                    int accountNumber = sc.nextInt();
                    sc.nextLine();
                    BankAccount existingBankAccount = findBankAccount(accounts, accountNumber);
                    // Check if account does exist
                    while (existingBankAccount == null) {
                        System.out.printf("Bank Account Number %d doesn't exist", accountNumber);
                        System.out.println();
                        System.out.print("Enter account number: ");
                        accountNumber = sc.nextInt();
                        sc.nextLine();
                        existingBankAccount = findBankAccount(accounts, accountNumber);
                    }
                    // Validation
                    if (existingBankAccount.getAvailableBalance() == 0)
                        System.out.println("Not enough balance to withdraw");
                    else {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = sc.nextDouble();
                        sc.nextLine();
                        // Amount validation
                        while(amount <=0 || amount > existingBankAccount.getAvailableBalance()) {
                            if (amount <=0){
                                System.out.println("Amount must be greater than 0" );
                            } else if (amount > existingBankAccount.getAvailableBalance()) {
                                System.out.println("Insufficient balance");
                            }
                            System.out.print("Enter withdrawal amount: ");
                            amount = sc.nextDouble();
                            sc.nextLine();
                        }
                        // update amount
                        existingBankAccount.withdraw(amount);
                        System.out.printf("Your current balance now is %.2f", existingBankAccount.getAvailableBalance());
                    }
                    break;
                }
                // Exit
                case 6:
                    keepTransacting = false;
                    break;

            }

            if(!keepTransacting)
                break;

            System.out.print("\nWould you like to return to the menu? (yes/no): ");
            String ans = sc.nextLine();
            if (!ans.equalsIgnoreCase("yes"))
                keepTransacting = false;


        }

        System.out.println("---Thank you!---");




    }
}