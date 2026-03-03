package com.Bank.dao.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.Bank.Util.HibernateUtil;
import com.Bank.dao.TransactionDAO;
import com.Bank.entity.Transaction;
import com.Bank.entity.enums.TransactionType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public void save(Transaction transaction) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			em.persist(transaction);
			et.commit();
		} catch (Exception e) {

		}

	}

	@Override
	public Optional<Transaction> FindById(Long transactionId) {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			return Optional.ofNullable(em.find(Transaction.class, transactionId));
		} finally {
			em.close();
		}

	}

	@Override
	public List<Transaction> findAll() {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			return em.createQuery("FORM Transaction", Transaction.class).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Transaction> findBySourceAccount(String accountNumber) {
		EntityManager em=HibernateUtil.getEntityManager();
		try {
            return em.createQuery(
                    "FROM Transaction t WHERE t.targetAccount.accountNumber = :acc",
                    Transaction.class)
                    .setParameter("acc", accountNumber)
                    .getResultList();
		}finally {
			em.close();
		}
		
	}

	@Override
	public List<Transaction> findByTargetAccount(String accountNumber) {
		
		
		EntityManager em=HibernateUtil.getEntityManager();
		try {
			
		return em.createQuery( "FORM Transaction t WHERE "+"t.sourceAccountNumber=: account"+ "OR t.targetAccoutn.AccountNumber= : acc",Transaction.class).setParameter("acc",accountNumber).getResultList();
		}finally {
			em.close();
		}
	}

	@Override
	public List<Transaction> findByAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findByType(TransactionType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findByDateRange(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long transactionId) {
		// TODO Auto-generated method stub

	}

}
