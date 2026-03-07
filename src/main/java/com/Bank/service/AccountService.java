package com.Bank.service;

import java.math.BigDecimal;
import java.util.List;

import com.Bank.entity.Account;
import com.Bank.entity.enums.AccountType;

/**
 * Handles all business logic related to banking operations.
 */
public interface AccountService {
	
	  /**
     * Create new bank account
     */
	Account createAccount(Long userId,AccountType accountType);
    
	 /**
     * Deposit money
     */
	void deposit(String accountNumber,BigDecimal amount);
	/**
     * Withdraw money
     */
	
	void withdraw(String accountNumber,BigDecimal amount);
	
	/**
     * Transfer funds between accounts
     */
	
	void transfer(String formAccount,String toAccount,BigDecimal amount);
	
	/**
     * Transfer funds between accounts
     */
	Account getAccount(String accountNumber);
	
	/**
     * Get all accounts of a user
     */
	List<Account> getAccountByUser(Long userId);
	

    /**
     * Get all accounts (Admin view)
     */
	List<Account>getAllAccount();
	
	 /**
     * Delete account (Admin only)
     */
	void deleteAccount(Long accountId);
	
	/**
     * Get current balance
     */
	BigDecimal getBalance(String accountNumber);

	List<Account> getAccountsByUser(Long userId);

	List<Account> getAllAccounts();
	
	
	
	
	
	
	
	
	
	
}
