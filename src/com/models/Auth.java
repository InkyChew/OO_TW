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
//	User newUser = null;

	public boolean createSession(User user) {
		System.out.println(user.userName + user.userPass);
		boolean auth = false;
		session = HibernateUtil.getSessionFactory().openSession();
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		try{
	         tx = session.beginTransaction();
	         List<User> data = session.createCriteria(User.class).add(Restrictions.eq("userName", user.userName)).list();
//	         List<User> data = session.createCriteria(User.class).add(Restrictions.eq("userName", "u1")).list();
	         if (data.size() > 0) {
	        	 User newUser = (User) data.get(0);
	        	 if (newUser.userPass.equals(user.userPass)) {
	        		 httpSession.setAttribute("userId", newUser.userId);
	        		 user = newUser;
	        		 auth = true;
		         }
	         }
	 		 data.clear();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return auth;
	}
	public Boolean checkSession() {
		 if(httpSession != null) { // 28
			 return true;
		 } else {
			 return false;
		 }
	}
	public void removeSession() {
		httpSession.removeAttribute("userId");
	}
	
	public void createOTP() {
		
	}
	public Boolean checkOTP() {
		return false;
	}
	
	public Boolean isAdmin(User user) {
		if (user.userRole.roleName.equals("administrator")) {
			return true;
		}
		return false;
	}
}
