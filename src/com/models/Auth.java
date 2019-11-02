package com.models;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class Auth {
	Session session = null;
	static HttpSession httpSession;
	Transaction tx = null;
	User newUser = null;
	
	//Singleton
	private static Auth auth = null;	
	private Auth() {	
	}	
	public static Auth getInstance() {
        if (auth == null){
            synchronized(Auth.class){
                if(auth == null) {
                     auth = new Auth();
                     httpSession = ServletActionContext.getRequest().getSession();
                }
            }
        }
        return auth;
    }
	//for test
	public static Auth getInstance(HttpSession hs) {
		httpSession = hs;
        if (auth == null){
            synchronized(Auth.class){
                if(auth == null) {
                     auth = new Auth();
                     
                }
            }
        }
        return auth;
    }
	public boolean createSession(User user) {
		boolean auth = false;
		session = HibernateUtil.getSessionFactory().openSession();
		try{
	         tx = session.beginTransaction();
	         List<User> data = session.createCriteria(User.class).add(Restrictions.eq("userName", user.userName)).list();
	         if (data.size() > 0) {
	        	 newUser = (User) data.get(0);
	        	 if (newUser.userPass.equals(user.userPass)) {
	        		 httpSession.setAttribute("userId", newUser.userId);
	        		 System.out.println("getUserId()");
	        		 System.out.println(newUser.userId);
	        		 System.out.println(httpSession.getAttribute("userId"));
	        		 System.out.println(getUserId());

	        		 System.out.println("getUserId()End");
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
		 if(httpSession.getAttribute("userId") == null) {
			 return false;
		 } else {
			 return true;
		 }
	}
	public void removeSession() {
		httpSession.removeAttribute("userId");
	}
	
	public int getUserId() {
		if (httpSession.getAttribute("userId") != null) {
			return (int) httpSession.getAttribute("userId");
		}
		return 0;
	}
	
	public void createOTP(String OTP, LocalDateTime expire) {
		httpSession.setAttribute("OTP", OTP);
		httpSession.setAttribute("OTPExpire", expire);
	}
	public Boolean checkOTP(String inputOTP) {
		String OTP = (String) httpSession.getAttribute("OTP");
		LocalDateTime expire = (LocalDateTime) httpSession.getAttribute("OTPExpire");
		final LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("+8")));
		if(inputOTP.equals(OTP) && now.isBefore(expire)) {
			return true;
		}
		return false;
	}
	
	public Boolean isAdmin() {
		if (newUser.userRole.roleName.equals("administrator")) {
			return true;
		}
		return false;
	}
	public User getUser(int userId) {
		User user = null;
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			 tx = session.beginTransaction();
			 List<User> data = session.createCriteria(User.class).add(Restrictions.eq("userId", userId)).list();
			 if (data.size() > 0) {
				 user = (User) data.get(0);
		     }
			 data.clear();
		     tx.commit();
		 }catch (HibernateException e) {
		     if (tx!=null) tx.rollback();
		     e.printStackTrace(); 
		 }finally {
		     session.close(); 
		 }
		return user;
	}
	public User getCurrentUser() {
		User user = null;
		int userId = 0;
		if (httpSession.getAttribute("userId")!= null) {
			userId = (int) httpSession.getAttribute("userId");
		}
		user = getUser(userId);
		return user;
	}
}
