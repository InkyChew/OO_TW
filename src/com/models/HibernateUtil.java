package com.models;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	public static SessionFactory factory;
	
	static {
		try{
			factory = new Configuration().configure().buildSessionFactory();
		}catch(Throwable ex){
			System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex); 
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	
	public static void closeFactory(){
		factory.close();
	}
}
