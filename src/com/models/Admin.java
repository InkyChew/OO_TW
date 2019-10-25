package com.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.controller.GUI;

public class Admin {
	private List<User> userList = new ArrayList<User>();
	
	public List<User> getAllUser() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try{
			List data = session.createCriteria(User.class)
	        		 .createAlias("userRole","role")
	        		 .add(Restrictions.not(Restrictions.eq("role.roleName", "administrator"))).list();
			for(Iterator iterator = data.iterator(); iterator.hasNext();){
	        	User user = (User) iterator.next();
	        	userList.add(user);
	        }
	 		data.clear();
	        tx.commit();
	     }catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	     }finally {
	        session.close(); 
	     }
		return userList;
	}
}
