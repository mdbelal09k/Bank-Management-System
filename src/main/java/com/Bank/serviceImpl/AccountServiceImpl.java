package com.Bank.serviceImpl;

import com.Bank.Util.HibernateUtil;
import com.Bank.dao.AccountDAO;
import com.Bank.dao.TransactionDAO;
import com.Bank.dao.UserDAO;
import com.Bank.dao.Impl.AccountDAOImpl;
import com.Bank.dao.Impl.TransactionDAOImpl;
import com.Bank.dao.Impl.UserDAOImpl;
import com.Bank.entity.Account;
import com.Bank.entity.Transaction;
import com.Bank.entity.User;
import com.Bank.entity.enums.AccountType;
import com.Bank.entity.enums.TransactionType;
import com.Bank.exception.InsufficientBalanceException;
import com.Bank.service.AccountService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AccountServiceImpl implements AccountService {

    private final AccountDAO accountDAO = new AccountDAOImpl() {
		
		@Override
		public Account findByAccountNumber(String accountNumber) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean existByAccountNumber(String accountNumber) {
			// TODO Auto-generated method stub
			return false;
		}
	};
    private final TransactionDAO transactionDAO = new TransactionDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();

    // =========================
    // Create Account
    // =========================
    @Override
    public Account createAccount(Long userId, AccountType accountType) {

        Optional<User> user =  userDAO.findById(userId);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        String accountNumber = "ACC-" + UUID.randomUUID().toString().substring(0, 8);

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.AccountType(accountType);
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);

        accountDAO.save(account);

        return account;
    }

    // =========================
    // Deposit
    // =========================
    @Override
    public void deposit(String accountNumber, BigDecimal amount) {

        Account account = accountDAO.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        account.setBalance(account.getBalance().add(amount));
        accountDAO.update(account);

        Transaction transaction = new Transaction(
                TransactionType.DEPOST,
                amount,
                null,
                account
        );

        transactionDAO.save(transaction);
    }

    // =========================
    // Withdraw
    // =========================
    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {

        Account account = accountDAO.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException();
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountDAO.update(account);

        Transaction transaction = new Transaction(
                TransactionType.WITHDRAW,
                amount,
                account,
                null
        );

        transactionDAO.save(transaction);
    }
    // =========================
    // Transfer (ACID SAFE)
    // =========================
    @Override
    public void transfer(String fromAccount, String toAccount, BigDecimal amount) {

        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            Account source = accountDAO.findByAccountNumber(fromAccount);
            Account target = accountDAO.findByAccountNumber(toAccount);

            if (source == null) {
                throw new RuntimeException("Source account not found");
            }

            if (target == null) {
                throw new RuntimeException("Target account not found");
            }

            if (source.getBalance().compareTo(amount) < 0) {
                throw new InsufficientBalanceException();
            }

            source.setBalance(source.getBalance().subtract(amount));
            target.setBalance(target.getBalance().add(amount));

            accountDAO.update(source);
            accountDAO.update(target);

            Transaction transaction = new Transaction(
                    TransactionType.TRANSFER,
                    amount,
                    source,
                    target
            );

            transactionDAO.save(transaction);

            tx.commit();

        } catch (Exception e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    // =========================
    // Get Account
    // =========================
    @Override
    public Account getAccount(String accountNumber) {

        Account account = accountDAO.findByAccountNumber(accountNumber);

        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        return account;
    }

    // =========================
    // Accounts by User
    // =========================
    @Override
    public List<Account> getAccountsByUser(Long userId) {
        return accountDAO.findByUserId(userId);
    }

    // =========================
    // All Accounts
    // =========================
    @Override
    public List<Account> getAllAccounts() {
        return accountDAO.findAll();
    }

    // =========================
    // Delete Account
    // =========================
    @Override
    public void deleteAccount(Long accountId) {
        accountDAO.delete(accountId);
    }

    // =========================
    // Get Balance
    // =========================
    @Override
    public BigDecimal getBalance(String accountNumber) {
        return getAccount(accountNumber).getBalance();
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
}