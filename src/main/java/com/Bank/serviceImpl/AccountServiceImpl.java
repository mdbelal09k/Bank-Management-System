package com.Bank.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.Bank.dao.AccountDAO;
import com.Bank.dao.TransactionDAO;
import com.Bank.dao.UserDAO;
import com.Bank.dao.Impl.AccountDAOImpl;
import com.Bank.dao.Impl.TransactionDAOImpl;
import com.Bank.dao.Impl.UserDAOImpl;
import com.Bank.entity.Account;
import com.Bank.entity.User;
import com.Bank.entity.enums.AccountType;
import com.Bank.entity.enums.TransactionType;
import com.Bank.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private final AccountDAO accountDAO = new AccountDAOImpl();
	private final TransactionDAO transactinDAO = new TransactionDAOImpl();
	private final UserDAO userDAO = new UserDAOImpl();

	// =========================
	// Create Account
	// =========================
	@Override
	public Account createAccount(Long userId, AccountType accountType) {

		User user = userDAO.findById(userId).orElseThrow(() -> new RuntimeException("User not found!"));

		String accountNumber = "ACC -" + UUID.randomUUID().toString().substring(0, 8);

		Account account = new Account(accountNumber, accountType, user);
		accountDAO.save(account);
		return account;

	}
	// =========================
	// Deposit
	// =========================

	@Override
	public void deposit(String accountNumber, BigDecimal amount) {

		Account account = accountDAO.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new RuntimeException("Account not found!"));
		account.setBalance(account.getBalance().add(amount));
		accountDAO.update(account);
		
		
		Transaction transaction =new Transaction(TransactionType.DEPOST,amount,null,account);
		transactionDAO.save(transaction);
	}

    // =========================
    // Withdraw
    // =========================
	@Override
	public void withdraw(String accountNumber, BigDecimal amount) {

		Account account =accountDAO.findByAccountNumber(accountNumber)

	}

	@Override
	public void transfer(String formAccount, String toAccount, BigDecimal amount) {
		// TODO Auto-generated method stub

	}

	@Override
	public Account getAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(Long accountId) {
		// TODO Auto-generated method stub

	}

	@Override
	public BigDecimal getBalance(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
