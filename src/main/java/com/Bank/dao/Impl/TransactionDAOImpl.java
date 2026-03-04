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
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			return em.createQuery("FROM Transaction t WHERE t.targetAccount.accountNumber = :acc", Transaction.class)
					.setParameter("acc", accountNumber).getResultList();
		} finally {
			em.close();
		}

	}

	@Override
	public List<Transaction> findByTargetAccount(String accountNumber) {

		EntityManager em = HibernateUtil.getEntityManager();
		try {

			return em
					.createQuery("FORM Transaction t WHERE " + "t.sourceAccountNumber=: account"
							+ "OR t.targetAccoutn.AccountNumber= : acc", Transaction.class)
					.setParameter("acc", accountNumber).getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Transaction> findByAccount(String accountNumber) {
		// TODO Auto-generated method stub
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			return em
					.createQuery("FROM Transaction  t WHERE t.sourceAccount.accountNmuber =: acc"
							+ " OR t.targetAccount.accountNumber =: acc", Transaction.class)
					.setParameter("acc", accountNumber).getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public List<Transaction> findByType(TransactionType type) {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			return em.createQuery("FROM Transaction t WHERE t.type =: type").setParameter("type", type).getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public List<Transaction> findByDateRange(LocalDateTime start, LocalDateTime end) {
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			return em.createQuery("FROM Transaction t WHERE t.transactionTime BETWEEN : start AND : end",
					Transaction.class).setParameter("start", start).setParameter("end", end).getResultList();

		} finally {
			em.close();
		}
	}

	@Override
	public void delete(Long transactionId) {

		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			Transaction txObj = em.find(Transaction.class, transactionId);
			if (txObj != null) {
				em.remove(txObj);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
				throw e;

			}
		} finally {
			em.close();
		}

	}

}
