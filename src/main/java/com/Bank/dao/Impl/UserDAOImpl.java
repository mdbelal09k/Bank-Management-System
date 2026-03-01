package com.Bank.dao.Impl;

import java.util.List;
import java.util.Optional;

import com.Bank.Util.HibernateUtil;
import com.Bank.dao.UserDAO;
import com.Bank.entity.User;
import com.Bank.entity.enums.Role;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UserDAOImpl implements UserDAO {

	@Override
	public void save(User user) {
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(user);
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

	@Override
	public void update(User user) {
		EntityManager em=HibernateUtil.getEntityManager();
		EntityTransaction et=em.getTransaction();
		
		try {
		et.begin();
		em.merge(user);
		et.commit();
		}catch(Exception e) {
			if(et.isActive()) {
				et.rollback();
			}
		}finally {
			em.close();
		}

	}

	@Override
	public void delete(Long usrid) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<User> findById(Long userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByRoll(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
