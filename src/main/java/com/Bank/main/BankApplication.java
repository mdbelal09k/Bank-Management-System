
package com.Bank.main;

import com.Bank.entity.Account;
import com.Bank.entity.enums.AccountType;
import com.Bank.entity.enums.Role;
import com.Bank.service.AccountService;
import com.Bank.service.AuthService;
import com.Bank.serviceImpl.AccountServiceImpl;
import com.Bank.serviceImpl.AuthServiceImpl;

import java.math.BigDecimal;

public class BankApplication {

    public static void main(String[] args) {

        System.out.println("===== BANK MANAGEMENT SYSTEM =====");

        AuthService authService = new AuthServiceImpl();
        AccountService accountService = new AccountServiceImpl();

        try {

            // =============================
            // Create Users
            // =============================

            var admin = authService.register("admin", "admin123", Role.ADMIN);
            var customer1 = authService.register("john", "1234", Role.CUSTOMER);
            var customer2 = authService.register("smith", "1234", Role.CUSTOMER);

            System.out.println("Users created successfully.");

            // =============================
            // Create Accounts
            // =============================

            Account acc1 =
                    accountService.createAccount(customer1.getId(), AccountType.SAVINGS);

            Account acc2 =
                    accountService.createAccount(customer2.getId(), AccountType.SAVINGS);

            System.out.println("Accounts created:");
            System.out.println(acc1.getAccountNumber());
            System.out.println(acc2.getAccountNumber());

            // =============================
            // Deposit
            // =============================

            accountService.deposit(acc1.getAccountNumber(),
                    new BigDecimal("5000"));

            System.out.println("Deposit successful.");

            // =============================
            // Withdraw
            // =============================

            accountService.withdraw(acc1.getAccountNumber(),
                    new BigDecimal("1000"));

            System.out.println("Withdrawal successful.");

            // =============================
            // Transfer
            // =============================

            accountService.transfer(
                    acc1.getAccountNumber(),
                    acc2.getAccountNumber(),
                    new BigDecimal("2000")
            );

            System.out.println("Transfer successful.");

            // =============================
            // Check Balance
            // =============================

            System.out.println("Balance Account 1: "
                    + accountService.getBalance(acc1.getAccountNumber()));

            System.out.println("Balance Account 2: "
                    + accountService.getBalance(acc2.getAccountNumber()));

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());

        }

        System.out.println("===== SYSTEM FINISHED =====");
    }
}