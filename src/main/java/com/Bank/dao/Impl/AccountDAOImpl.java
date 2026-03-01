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
		}finally {
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
	        	
	        }catch(Exception e) {
	        	if(et.isActive()) {
	        		et.rollback();
	        		throw e;
	        	}
	        }finally {
	        	em.close();
	        }

	}

	@Override
	public void delete(Long accountId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Account> findById(Long accountId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findByType(AccountType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existByAccountNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
