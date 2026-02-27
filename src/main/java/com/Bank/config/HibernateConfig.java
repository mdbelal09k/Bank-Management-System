package com.Bank.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateConfig {
	
	private static final EntityManagerFactory emf=Persistence.createEntityManagerFactory("bankPU");
	
	public static EntityManager getEntityManger() {
		return emf.createEntityManager();
	}

}
