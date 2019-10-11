package com.controller;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.text.DateFormat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.models.HibernateUtil;
import com.models.Mail;
import com.models.User;
import com.models.UserInfo;
import com.models.TransactionDetail;
import com.models.Transfer;
import com.models.AbTransfer;
import com.models.Payment;
import com.models.Auth;
import com.models.Deposit;
import com.models.Admin;

// Facade+Mediator
public class GUI { //GUI=V+C
	private User user = new User();
	private Transfer transfer;
	private AbTransfer abTransfer;
	private TransactionDetail transactionDetail;
	private List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();
	private List<User> userList = new ArrayList<User>();
	
	Session session = null;
	Transaction tx = null;
	
	Mail mail = new Mail();
	Auth auth = Auth.getInstance(); //Singleton
	
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
	public AbTransfer getAbtransfer() {
		return abTransfer;
	}
	public void setAbtransfer(AbTransfer abT) {
		abTransfer = abT;
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
	
	public String toDeposit() {
		return "success";
	}
	public String toReceivement() {
		return "success";
	}
	public String toInformation() {
		return "success";
	}
	
	public String login() {
		String output = "error";
		if(auth.createSession(user)) {
			if(auth.isAdmin()) { // account detail page
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
			         output = "administrator";
			 		 data.clear();
			         tx.commit();
			      }catch (HibernateException e) {
			         if (tx!=null) tx.rollback();
			         e.printStackTrace(); 
			      }finally {
			         session.close(); 
			      }
			}else {
				output = "success"; // menu
			}
		} else {
			output = "error"; // error page
		}
		return output;
	}

	public String showPayOTPView() { // �I�ڤH
		String output = "error";
		if(auth.checkSession()) {
			// sendOTP
			String OTP = "";
			for(int i = 0; i < 8; i++){
		      int random = (int)((Math.random() * 3) + 1);
		      if(i == 1){
		    	OTP += (char)(int)((Math.random()*10)+48);
		      }else if(i == 2){
		        OTP += (char)(int)(((Math.random()*26) + 65));
		      }else{
		        OTP += (char)(int)((Math.random()*26) + 97);
		      }
		    }
			String address = user.getUserInfo().getEmail();
			mail.sendOTP(address, OTP);
			SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
			final LocalDateTime expire = LocalDateTime.now(Clock.system(ZoneId.of("+8"))).plusMinutes(10);
			auth.saveOTP(OTP, expire);
			// show pay
			return "payment";
		}
		return output;
	}
	public String transactionSuccessView() {
		String output = "error";
		if(auth.checkSession()) {
			// �O���ϥΪ̿�J����
			int traderId=transfer.traderId;
			int amount= transfer.amount;
			String OTP = transfer.otp;
			transfer= new Payment();
			transfer.setAmount(amount);
			transfer.setTraderId(traderId);
			
			if(auth.checkOTP(OTP)) {
				// transaction
				// record transaction payment & receivement
				output = transfer.process();
			} else {
				output = "error";// error page
			}
		} else {
			output = "error";
		}
		return output;
	}
	
	public String deposit() {
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		int amount= transfer.amount;
		int userId = (int) httpSession.getAttribute("userId");
		abTransfer = new AbTransfer(userId, userId, amount, new Deposit());
//		transfer = new Deposit();
//		transfer.setAmount(amount);
		String output = abTransfer.process();
//		if (output=="success") {
//			output = toPlatform();
//		}
		return output;
	}
	
	public String checkTransactionHistory() {
		String output = "error";
		session = HibernateUtil.getSessionFactory().openSession();
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		if(auth.checkSession()) {
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
		}
		System.out.println(output);
		return output;
	}
	
	public String logout() {
		auth.removeSession();
		String output = "success";
		return output; // login page
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