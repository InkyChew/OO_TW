package com.models;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AccountHandler extends LoginHandler {
	Session session = HibernateUtil.getSessionFactory().openSession();
	
	AccountHandler(LoginHandler s){
		super(s);
	}
	public boolean canHandle() { // checkUserName
		boolean isValid = false;
		Transaction tx = session.beginTransaction();
		List<User> data = session.createCriteria(User.class).add(Restrictions.eq("userName", user.userName)).list();
		try {
			if(data.size() <= 0) {
				isValid = true;
			}
		}catch(HibernateException e) {
			if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
		}finally {
			session.close();
		}
		return isValid;
	}
	public void handleRequest() {
		if(canHandle()) {
			failTimes += 1;
			setFailTimes(failTimes);
			setErrorMsg("Account is not available.");
		} else {
			super.handleRequest();
		}
	}
}
