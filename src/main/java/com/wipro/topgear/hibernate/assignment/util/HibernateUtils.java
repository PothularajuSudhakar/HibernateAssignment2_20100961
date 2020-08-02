package com.wipro.topgear.hibernate.assignment.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtils {

	private static SessionFactory sessionFactory;

	static {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties())
				.build();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void cleanSessionFactory() {
		sessionFactory.close();
	}
}
