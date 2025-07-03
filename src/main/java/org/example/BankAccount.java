package org.example;

public class BankAccount {
    private int accountNumber;
    private String bankAccountHolderName;
    private double availableBalance;

    public BankAccount () {

    }

    public BankAccount (int accountNumber, String bankAccountHolderName) {
        this.accountNumber = accountNumber;
        this.bankAccountHolderName = bankAccountHolderName;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankAccountHolderName() {
        return bankAccountHolderName;
    }

    public void setBankAccountHolderName(String bankAccountHolderName) {
        this.bankAccountHolderName = bankAccountHolderName;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }

    public void deposit(double amount) {
        if (amount >= 0.1)
            this.availableBalance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= this.availableBalance && amount >=0.1)
            this.availableBalance -= amount;
    }

    public void displayInformation() {
        System.out.printf("Account Number: %d\n", this.accountNumber);
        System.out.printf("Account Number: %s\n", this.bankAccountHolderName);
        System.out.printf("Account Number: %.2f\n", this.availableBalance);
        System.out.println("----------------------------");
    }
}
