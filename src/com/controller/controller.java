package com.controller;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.models.HibernateUtil;
import com.models.User;
import com.models.UserInfo;
import com.models.TransactionDetail;
import com.models.Transfer;
import com.models.Payment;
import com.models.Deposit;
public class controller {
	private User user = new User();
	private Transfer transfer;
	private TransactionDetail transactionDetail;
	private List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();
	private List<User> userList = new ArrayList<User>();
	private int timeSearchYear;
	private int timeSearchMonth;	
	public int getTimeSearchYear() {
		return timeSearchYear;
	}
	public void setTimeSearchYear(int timeSearchYear) {
		this.timeSearchYear = timeSearchYear;
	}
	public int getTimeSearchMonth() {
		return timeSearchMonth;
	}
	public void setTimeSearchMonth(int timeSearchMonth) {
		this.timeSearchMonth = timeSearchMonth;
	}
	Session session = null;
	Transaction tx = null;
	public void setUser(User user){
	    this.user=user;
	}
	public User getUser(){
	    return user;
	}
	public void setTransfer(Transfer transfer){
	    this.transfer=transfer;
	}
	public Transfer getTransfer(){
	    return transfer;
	}
	public void setTransactionDetails(List<TransactionDetail> transactionDetails){
	    this.transactionDetails=transactionDetails;
	}
	public List<TransactionDetail> getTransactionDetails(){
	    return transactionDetails;
	}
	public void setUserList(List<User> userList){
	    this.userList=userList;
	}
	public List<User> getUserList(){
	    return userList;
	}
	public void SetTransferTransactionDetail(int amount, int balance,	String type, int walletId, int traderId){ // class Transfer setTransactionDetail 30 60
		DateFormat dfcurrentTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dfcurrentTime.format(new java.util.Date());
		transactionDetail.setDate(date);
		transactionDetail.setAmount(amount);
		transactionDetail.setBalance(balance);
		transactionDetail.setType(type);
		transactionDetail.setWalletId(walletId);
		transactionDetail.setTraderId(traderId);
	}
	public Boolean isAdmin(User user) {
		if (user.userRole.roleName.equals("administrator")) {
			return true;
		}
		return false;
	}
	public String readClientAll() {
		String output = "error";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();;
		try{
			if (isAdmin(user)) {
		         List data = session.createCriteria(User.class)
		        		 .createAlias("userRole","role")
		        		 .add(Restrictions.not(Restrictions.eq("role.roleName", "administrator"))).list();
		         for(Iterator iterator = data.iterator(); iterator.hasNext();){
		        	 User user = (User) iterator.next();
		        	 userList.add(user);
		         }
		         output = "administrator";
		 		 data.clear();
		         tx.commit();
			}
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return output;
	}
	
	public String login() {
		String output = "error";
		session = HibernateUtil.getSessionFactory().openSession();
		HttpSession httpSession = ServletActionContext.getRequest().getSession(); 
		try{	
	         tx = session.beginTransaction();
	         List<User> data = session.createCriteria(User.class).add(Restrictions.eq("userName", user.userName)).list();
	         if (data.size() >0) {
	        	 User newUser = (User) data.get(0);
	        	 if ( newUser.userPass.equals(user.userPass)) {
	        		 httpSession.setAttribute("userId", newUser.userId);
	        		 output="success";
	        		 if (isAdmin(newUser)) {
	        			 output = readClientAll();
	        		 }
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
		return output;
	}
	public String payment() {
		int traderId=transfer.traderId;
		int amount= transfer.amount;
		transfer= new Payment();
		transfer.setAmount(amount);
		transfer.setTraderId(traderId);	
		String output = transfer.process();
		if (output=="success") {
			output = toPlatform();
		}
		return output;
	}
	public String deposit() {
		int amount= transfer.amount;
		transfer= new Deposit();
		transfer.setAmount(amount);
		String output = transfer.process();
		if (output=="success") {
			output = toPlatform();
		}
		return output;
	}
	public String QRpay() {
		String output="success";
		return output;
	}
	public String showTransaction() {
		String output="error";
		session = HibernateUtil.getSessionFactory().openSession();
		HttpSession httpSession = ServletActionContext.getRequest().getSession(); 
		try{
	         tx = session.beginTransaction();
    		 TransactionDetail transactionDetail;
    		 int userId = (int) httpSession.getAttribute("userId");
    		 List data = session.createCriteria(User.class).add(Restrictions.eq("userId", userId)).list();
    		 int walletId = ((User) data.get(0)).wallet.walletId;
	         data = session.createCriteria(TransactionDetail.class).add(Restrictions.eq("walletId", walletId)).list();
	         
	         for (int i = 0; i < data.size(); i++) {
	        	 transactionDetail = (TransactionDetail) data.get(i);
	        	 transactionDetails.add(transactionDetail);
	         }
	 		 data.clear();
	         tx.commit();
	         output="success";
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return output;
	}
	public String toPlatform() {
		String output = "error";
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			HttpSession httpSession = ServletActionContext.getRequest().getSession(); 
			int userId = (int) httpSession.getAttribute("userId");
			List data = session.createCriteria(User.class).add(Restrictions.eq("userId", userId)).list();
			user = (User) data.get(0);
			output = "success";
		}catch (HibernateException e){
			if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return output;
	}
}
