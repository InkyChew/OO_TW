package com.models;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class Auth {
	Session session = null;
	HttpSession httpSession = null;
	Transaction tx = null;

	public boolean createSession(User user) {
		boolean auth = false;
		session = HibernateUtil.getSessionFactory().openSession();
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		try{
	         tx = session.beginTransaction();
	         List<User> data = session.createCriteria(User.class).add(Restrictions.eq("userName", user.userName)).list();
	         if (data.size() > 0) {
	        	 user = (User) data.get(0);
	        	 if ( user.userPass.equals(user.userPass)) {
	        		 httpSession.setAttribute("userId", user.userId);
	        		 auth = true;
//	        		 if (isAdmin(user)) {
//	        			 output = readClientAll();
//	        		 }
		         }
	         }
	 		 data.clear();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace();
	      }
		return auth;
	}
	public void removeSession(int userId) {
		session.close();
	}
	public Boolean checkSession() {
		 if(httpSession != null) { // 28
			 return true;
		 } else {
			 return false;
		 }
	}
	public Boolean isAdmin(User user) {
		if (user.userRole.roleName.equals("administrator")) {
			return true;
		}
		return false;
	}
}
