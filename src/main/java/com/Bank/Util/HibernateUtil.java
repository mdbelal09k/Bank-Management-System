package com.Bank.Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");

	public static final EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
