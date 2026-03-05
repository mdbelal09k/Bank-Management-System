package com.Bank.dao;

import java.lang.classfile.ClassFile.Option;
import java.util.List;
import java.util.Optional;

import com.Bank.entity.Account;
import com.Bank.entity.enums.AccountType;

/*
 * public interface for account entity.
 * Handle all account-related database opetations.
 */
public interface AccountDAO {
	///-> create account
	void save(Account account);
	///-> update acccount (used in deposite/withdraw/transafer)

	void update(Account account);
	///->delete
	void delete(Long accountId);
	///-> find account by ID
	Optional<Account> findById(Long accountId);
	///-> get all account
	List <Account> findAll();
	///-> get account by UserId
	List<Account> findByUserId(Long userId);
	///-> get account by type (SAVING/CURRENT
	List<Account>findByType(AccountType type);
	///->check if account number already exists
	boolean existByAccountNumber(String accountNumber);
	Account findByAccountNumber(String accountNumber);
	}
