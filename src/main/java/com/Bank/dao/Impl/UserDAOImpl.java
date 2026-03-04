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
		EntityManager em = HibernateUtil.getEntityManager();
		EntityTransaction et = em.getTransaction();

		try {
			et.begin();
			em.merge(user);
			et.commit();
		} catch (Exception e) {
			if (et.isActive()) {
				et.rollback();
			}
		} finally {
			em.close();
		}

	}

	@Override
	public void delete(Long userId) {
		EntityManager em =HibernateUtil.getEntityManager();
		EntityTransaction et=em.getTransaction();
		
		try {
			et.begin();
			User user=em.find(User.class,userId);
			
			if(user!=null) {
				em.remove(user);
			}
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
	public Optional<User> findById(Long userId) {
     EntityManager em=HibernateUtil.getEntityManager();
     
     try {
    	User user=em.find(User.class,userId);
    	return Optional.ofNullable(user);
    	}finally {
    		em.close();
    	}
		
	}

	@Override
	public Optional<User> findByUsername(String username) {
		EntityManager em=HibernateUtil.getEntityManager();
		
		try {
			
			List<User> list=em.createQuery("FROM User u WHERE u.username= :username",User.class).setParameter("username",username).getResultList();;
			return Optional.empty();
		}finally {
			em.close();
		}
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		EntityManager em=HibernateUtil.getEntityManager();
		try {
			return em.createQuery("FORM User",User.class).getResultList();
		}
		finally {
			em.close();
		}
	}

	@Override
	public List<User> findByRoll(Role role) {
		EntityManager em=HibernateUtil.getEntityManager();
		try {
			return em.createQuery("FORM User u.role= :role",User.class).setParameter("role",role).getResultList();
			
		}
		finally {
			em.close();
		}
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return findByUsername(username).isPresent();
	}

}
