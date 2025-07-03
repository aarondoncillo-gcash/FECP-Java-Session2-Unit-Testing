package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount bankAccount;

    @BeforeEach
    void setup() {
        bankAccount = new BankAccount(456, "Tony");
    }

    @Test
    void testDepositValidAmount() {
        bankAccount.deposit(200);
        assertEquals(200,bankAccount.getAvailableBalance());
    }

    @Test
    void testDepositInvalidAmount() {
        bankAccount.deposit(-120);
        assertEquals(0,bankAccount.getAvailableBalance());
    }

    @Test
    void testWithdrawValidAmount() {
        bankAccount.setAvailableBalance(1000);
        bankAccount.withdraw(450);
        assertEquals(550,bankAccount.getAvailableBalance());
    }

    @Test
    void testWithdrawInvalidAmount() {
        bankAccount.setAvailableBalance(1000);
        bankAccount.withdraw(-123);
        assertEquals(1000,bankAccount.getAvailableBalance());
    }

    @Test
    void testWithdrawAmountExceedingBalance() {
        bankAccount.setAvailableBalance(1000);
        bankAccount.withdraw(3000);
        assertEquals(1000,bankAccount.getAvailableBalance());
    }

    @Test
    void testGetAccountNumber() {
        bankAccount.setAccountNumber(345);
        assertEquals(345, bankAccount.getAccountNumber());
    }

    @Test
    void testCreatedBankAccountWithoutDeposits() {
        assertEquals(456, bankAccount.getAccountNumber());
        assertEquals("Tony", bankAccount.getBankAccountHolderName());
        assertEquals(0, bankAccount.getAvailableBalance());
    }
}