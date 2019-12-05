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
	
	public Auth(){
		httpSession = ServletActionContext.getRequest().getSession();
	}
	
	// for test
	public Auth(HttpSession hs){
		httpSession = hs;
	}
	
	public void setFailTimes(int failTimes) {
		httpSession.setAttribute("failTimes", failTimes);
		final LocalDateTime expire = LocalDateTime.now(Clock.system(ZoneId.of("+8"))).plusMinutes(10);
		httpSession.setAttribute("failTimesExpire", expire);
	}
	
	public int getFailTimes() {
		if(httpSession.getAttribute("failTimes") == null || httpSession.getAttribute("failTimesExpire") == null) {
			 return 0;
		 } else {
			 int failTimes = (int) httpSession.getAttribute("failTimes");
			 LocalDateTime expire = (LocalDateTime) httpSession.getAttribute("failTimesExpire");
			 final LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("+8")));
				if(now.isBefore(expire)) {
					return failTimes;
				}
			 return 0;
		 }
	}
	
	public boolean checkStr(String str) {
		boolean result = false;
		if (str != null && str.length() > 0 && str.length() <= 10) {
			result = true;
		}
		return result;
	}
	
	public boolean createSession() {
		LoginHandler pasHandler = new PasswordHandler(null);
		LoginHandler accHandler = new AccountHandler(pasHandler);
		LoginHandler inpHandler = new InputHandler(accHandler);
		LoginHandler failHandler = new FailTimeHandler(inpHandler);
		
		failHandler.handleRequest();

		return checkSession();
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
		if (getCurrentUser().userRole.roleName.equals("administrator")) {
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
	public boolean userExist(String username) {
		boolean result = false;
		session = HibernateUtil.getSessionFactory().openSession();
		try{
			 tx = session.beginTransaction();
			 List<User> data = session.createCriteria(User.class).add(Restrictions.eq("userName", username)).list();
			 if (data.size() > 0) {
				 result = true;
		     }
			 data.clear();
		     tx.commit();
		 }catch (HibernateException e) {
		     if (tx!=null) tx.rollback();
		     e.printStackTrace(); 
		 }finally {
		     session.close(); 
		 }
		return result;
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
	
	public String CreatOrCTID() {
		String OrCTID = "";
		for(int i = 0; i < 8; i++){
	      int random = (int)((Math.random() * 3) + 1);
	      if(random == 1){
	    	  OrCTID += (char)(int)((Math.random()*10)+48);
	      }else if(random == 2){
	    	  OrCTID += (char)(int)(((Math.random()*26) + 65));
	      }else{
	    	  OrCTID += (char)(int)((Math.random()*26) + 97);
	      }
	    }
		httpSession.setAttribute("OrCTID", OrCTID);
		return OrCTID;
	}
	
	public String getOrCTID() {
		if (httpSession.getAttribute("OrCTID") != null) {
			return (String) httpSession.getAttribute("OrCTID");
		} else {
			return null;
		}
	}
	
	public void clearOrCTID() {
		httpSession.removeAttribute("OrCTID");
	}
	
	public void createUser(String email, String username, String telephone,
			String address, String password, String name) {
		UserInfo userinfo = new UserInfo();
		userinfo.setTelephone(telephone);
		userinfo.setEmail(email);
		userinfo.setName(name);
		userinfo.setAddress(address);
		Wallet wallet = new Wallet();
		User newUser = new User();
		newUser.setUserName(username);
		newUser.setUserPass(password);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try{
			userinfo = (UserInfo) session.merge(userinfo);
			wallet = (Wallet)session.merge(wallet);
			newUser.setWallet(wallet);
			newUser.setUserInfo(userinfo);
			Role role;
			List<Role> data = session.createCriteria(Role.class).add(Restrictions.eq("roleId", 1)).list();
			role = (Role) data.get(0);
			newUser.setUserRole(role);
			newUser = (User) session.merge(newUser);
			data.clear();
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
	}
}
