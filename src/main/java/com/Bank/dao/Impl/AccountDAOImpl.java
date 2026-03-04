package com.Bank.dao.Impl;

import java.util.List;
import java.util.Optional;

import com.Bank.Util.HibernateUtil;
import com.Bank.dao.AccountDAO;
import com.Bank.entity.Account;
import com.Bank.entity.enums.AccountType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public void save(Account account) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(account);

			tx.commit();
		} catch (Exception e) {
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Account account) {

		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(account);
			et.commit();

		} catch (Exception e) {
			if (et.isActive()) {
				et.rollback();
				throw e;
			}
		} finally {
			em.close();
		}

	}

	@Override
	public void delete(Long accountId) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Account account = em.find(Account.class, accountId);
			if (account != null) {
				em.remove(account);
			}
		} catch (Exception e) {
			if (et.isActive()) {
				et.rollback();
			}
		} finally {
			em.close();
		}

	}

	@Override
	public Optional<Account> findById(Long accountId) {
		EntityManager em = HibernateUtil.getEntityManager();
		try {

			return Optional.ofNullable(em.find(Account.class, accountId));
		} catch (Exception e) {
			em.close();
		}
		return Optional.empty();

	}

	@Override
	public List<Account> findAll() {
		EntityManager em = HibernateUtil.getEntityManager();

		try {
			return em.createQuery("FORM Account", Account.class).getResultList();
		} finally {
			em.clear();
		}
	}

	@Override
	public List<Account> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		EntityManager em=HibernateUtil.getEntityManager();
		try {
			return em.createQuery("FORM Account a WHERE a.user.id= : id",Account.class).setParameter("uid",userId).getResultList();
		}finally {
			em.close();
		}
	}

	@Override
	public List<Account> findByType(AccountType type) {
		EntityManager em=HibernateUtil.getEntityManager();
		try {
			
			return em.createQuery("FROM Account a WHERE a.type =: type",Account.class).setParameter("type",type).getResultList();
		}finally {
			em.close();
		}
	}

	@Override
	public boolean existByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return  findByAccountNumber(accountNumber).isPresent();
	}

	private Optional<Account> findByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
