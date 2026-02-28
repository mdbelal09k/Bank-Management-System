package com.Bank.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.Bank.entity.Transaction;
import com.Bank.entity.enums.TransactionType;

/*
 * DAO interface for transaction entity.
 * Handle all transaction history and reporting operations.
 * 
 */
public interface TransactionDAO {
	
	///->  save new transacton (deposit / withdraw / transfer)
	
	void save(Transaction transaction);
	///-> find transaction by ID
   Optional<Transaction> FindById(Long transaction);
    ///-> get all transactions
     List<Transaction> findAll();
     ///-> // Get transactions by source account
     List<Transaction> findBySourceAccount(String accountNumber);
     ///->  Get transactions by target account
     List<Transaction> findByTargetAccount(String accountNumber);
     ///->Get all transactions of an account (incoming + outgoing)
     List<Transaction> findByAccount(String accountNumber);
     ///->Get transactions by type
     List<Transaction> findByType(TransactionType type);
     ///-> Get transactions between date range (for reports)
    List<Transaction> findByDateRange(LocalDateTime start,LocalDateTime end);
     ///-> delete transaction (admin only)
     void delete(Long transactionId);
     
     


	
}
