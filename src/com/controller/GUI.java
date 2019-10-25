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
import com.models.Receivement;
import com.models.Auth;
import com.models.Deposit;
import com.models.Admin;

// Facade+Mediator
public class GUI { //GUI=V+C
	private User user = new User();
	private Admin admin = new Admin();
	private Transfer transfer;
	private AbTransfer abTransfer;
	private TransactionDetail transactionDetail;
	private List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();
	private List<User> userList = new ArrayList<User>();
	private Mail mail;
	private Auth auth;
	
	// DB
	Session session = null;
	Transaction tx = null;
	
	public GUI() {
		this.mail = new Mail();
		this.auth = Auth.getInstance(); //Singleton
	}
	
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
	public void setAbtransfer(AbTransfer abT) {
		abTransfer = abT;
	}
	public AbTransfer getAbtransfer() {
		return abTransfer;
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
		String output = "error";
		if ((user = auth.getCurrentUser()) != null) {
			output = "success";
		}
		return output;
	}
	public String deposit() {
		String output = "error";
		if(auth.checkSession()) {
			int userId = auth.getUserId();
			int amount= transfer.amount;
			abTransfer = new AbTransfer(userId, userId, amount, new Deposit());
			output = abTransfer.process();
		}
		return output;
	}
	public String login() {
		String output = "error";
		if(auth.createSession(user)) {
			if(auth.isAdmin()) { // account detail page
				userList = admin.getAllUser();
				output = "administrator";
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
		      if(random == 1){
		    	OTP += (char)(int)((Math.random()*10)+48);
		      }else if(random == 2){
		        OTP += (char)(int)(((Math.random()*26) + 65));
		      }else{
		        OTP += (char)(int)((Math.random()*26) + 97);
		      }
		    }
			int userId = auth.getUserId();
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			try{
		         List data = session.createCriteria(User.class)
		        		 .add(Restrictions.eq("userId", userId)).list();
		         for(Iterator iterator = data.iterator(); iterator.hasNext();){
		        	 user = (User) iterator.next();
		         }
		 		 data.clear();
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
			String address = user.getUserInfo().getEmail();
			mail.sendOTP(address, OTP);
			final LocalDateTime expire = LocalDateTime.now(Clock.system(ZoneId.of("+8"))).plusMinutes(10);
			auth.createOTP(OTP, expire);
			// show pay
			return "success";
		}
		return output;
	}
	public String payment() {
		String output = "error";
		if(auth.checkSession()) {
			System.out.println("session pass");
			int traderId=transfer.traderId;
			int amount= transfer.amount;
			String otp = transfer.otp;
			if(auth.checkOTP(otp)) {
				abTransfer = new AbTransfer(auth.getUserId(), traderId, amount, new Payment());
				output = abTransfer.process();
			} else {
				output = "error";
			}
		} else {
			output = "error";
		}
		return output;
	}
	public void receivement(int userId, int traderId, int amount) {
		abTransfer = new AbTransfer(userId, traderId, amount, new Receivement());
		abTransfer.process();
	}
	public String checkTransactionHistory() {
		String output = "error";
		session = HibernateUtil.getSessionFactory().openSession();
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		if(auth.checkSession()) {
			try{
		         tx = session.beginTransaction();
//		   		 TransactionDetail transactionDetail;
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
		int userId = auth.getUserId();
		user = auth.getUser(userId);
		output = "success";
		return output;
	}	
	
	public String setTransferDetail(Transfer transfer, int walletId) {
		DateFormat dfcurrentTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dfcurrentTime.format(new java.util.Date());
		transactionDetail = new TransactionDetail();
		transactionDetail.setDate(date);
		transactionDetail.setAmount(transfer.amount);
		transactionDetail.setBalance(transfer.balance);
		transactionDetail.setType(transfer.type);
		transactionDetail.setWalletId(walletId);
		transactionDetail.setTraderId(transfer.traderId);
		return transfer.updateTransactionDetail(transactionDetail);
	}
	
	public User getAuthUser(int userId) {
		return auth.getUser(userId);
	}
}