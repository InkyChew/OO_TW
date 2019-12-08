package com.controller;

import com.opensymphony.xwork2.ActionSupport;


import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
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
import com.models.OrCtStored;
import com.models.User;
import com.models.UserInfo;
import com.models.Wallet;
import com.models.TransactionDetail;
import com.models.Transfer;
import com.models.AbTransfer;
import com.models.Payment;
import com.models.Receivement;
import com.models.RegisterCareTaker;
import com.models.RegisterOriginator;
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
	
	private RegisterOriginator registerOriginator;
	private RegisterCareTaker registerCareTaker;
	private OrCtStored orCtStored;
	
	// DB
	Session session = null;
	Transaction tx = null;
	
	public GUI() {
		this.mail = Mail.getInstance(); //Singleton
		this.auth = new Auth();
	}
	// for testing
	public GUI(HttpSession hs) {
		this.mail = Mail.getInstance(); //Singleton
		this.auth = new Auth(hs); // testing
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
	public void setAbTransfer(AbTransfer abT) {
		abTransfer = abT;
	}
	public AbTransfer getAbTransfer() {
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
	public String toPlatform() {
		String output = "error";
		if(auth.checkSession()){
			output = "success";
			if(auth.isAdmin()) {
				output = "administrator";
			}
		}
		return output;
	}	
	public String toDeposit() {
		String output = "error";
		if(auth.checkSession()) {
			output = "success";
		}
		return output;
	}
	public String ToScan() {
		String output = "error";
		if(auth.checkSession()) {
			output = "success";
		}
		return output;
	}
	public String toReceivement() {
		String output = "error";
		if(auth.checkSession()) {
			user = auth.getCurrentUser();
			output = "success";
		}
		return output;
	}
	public String toInformation() {
		String output = "error";
		if(auth.checkSession()) {
			user = auth.getCurrentUser();
			output = "success";
		}
		return output;
	}
	public String toServiceDetails() {
		String output = "error";
		if(auth.checkSession()) {
			user = auth.getCurrentUser();
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
		if(auth.createSession()) {
			if(auth.isAdmin()) {
				output = "administrator";
			}else {
				output = "success";
			}
		} else {
			output = "error";
		}
		return output;
	}

	public String showPayOTPView() {
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
			user = auth.getCurrentUser();
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
	// for testing
	public void receivement(int userId, int traderId, int amount, HttpSession hs) {
		abTransfer = new AbTransfer(userId, traderId, amount, new Receivement(hs));
		abTransfer.process();
	}
	public String checkTransactionHistory() {
		String output = "error";
		session = HibernateUtil.getSessionFactory().openSession();
		if(auth.checkSession()) {
			try{
		         tx = session.beginTransaction();
		   		 int userId = auth.getUserId();
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
		return output;
	}
	
	public String logout() {
		auth.removeSession();
		String output = "success";
		return output; // login page
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
		return transactionDetail.updateTransactionDetail();
	}
	
	public User getAuthUser(int userId) {
		return auth.getUser(userId);
	}

	public String readClientAll() {
		String output = "error";
		if (auth.checkSession()) {
			if (auth.isAdmin()) {
		         userList = admin.getAllUser();
		         output = "administrator";
			}
		}
		return output;
	}
	
	private void setOrCt(String key) {
		this.registerOriginator = this.orCtStored.getRegisterOriginator(key);
		this.registerCareTaker = this.orCtStored.getRegisterCareTaker(key);
	}
	
	public String toRegisterContract() {
		this.orCtStored = OrCtStored.getInstance();
		String result = "error";
		HttpServletRequest httpRequest = ServletActionContext.getRequest();
		String method = (String) httpRequest.getMethod();
		String type = (String)httpRequest.getParameter("type");
		String key = this.auth.getOrCTID();
		if (method.equals("POST")) {
			if (type.equals("back")) {
				this.registerCareTaker = null;
				this.registerOriginator = null;
				this.orCtStored.removeOrCT(key);
				this.auth.clearOrCTID();
				result = "back";
			} else {
				this.setOrCt(key);
				String contract = (String)httpRequest.getParameter("contract");
				if (contract == null) {
					httpRequest.setAttribute("msg", "please accept the contract.");
					return "wrong";
				} else {
					this.registerOriginator.setContract(true);
				}
				this.registerCareTaker.addMemento(this.registerOriginator.saveToMemento());
				this.registerOriginator.setState(1);
				result = "next";
			}
		} else { // GET
			if (key == null) {
				key = this.auth.CreatOrCTID();
				while(!this.orCtStored.OrCTIDIsEmpty(key)) {
					key = this.auth.CreatOrCTID();
				}
				this.registerOriginator = this.orCtStored.addRegisterOriginator(key);
				this.registerCareTaker = this.orCtStored.addRegisterCareTaker(key);
				result = "success";
			} else {
				this.setOrCt(key);
				if (this.registerOriginator.getState() != 0) {
					result = "next";
				} else {
					httpRequest.setAttribute("contract", this.registerOriginator.getContract());
					result = "success";
				}
			}
		}
		return result;
	}
	
	public String toRegisterInfo() {
		this.orCtStored = OrCtStored.getInstance();
		String result = "error";
		HttpServletRequest httpRequest = ServletActionContext.getRequest();
		String method = (String) httpRequest.getMethod();
		String type = (String)httpRequest.getParameter("type");
		String key = this.auth.getOrCTID();
		if (method.equals("POST")) {
			this.setOrCt(key);
			if (type.equals("back")) {
				this.registerOriginator.restoreFromMemento(this.registerCareTaker.getLastMemento());
				result = "back";
			} else {
				String email = (String) httpRequest.getParameter("email");
				String username = (String) httpRequest.getParameter("username");
				String name = (String) httpRequest.getParameter("name");
				String telephone = (String) httpRequest.getParameter("telephone");
				String address = (String) httpRequest.getParameter("address");
				this.registerOriginator.setEmail(email);
				this.registerOriginator.setUsername(username);
				this.registerOriginator.setName(name);
				this.registerOriginator.setTelephone(telephone);
				this.registerOriginator.setAddress(address);
				if(email.equals("") || username.equals("") || name.equals("") || telephone.equals("") || address.equals("")) {
					httpRequest.setAttribute("msg", "All fields are required.");
					return "wrong";
				} else if (username.length() > 10) {
					httpRequest.setAttribute("msg", "Your username must be under or equal than 10 characters long.");
					return "wrong";
				} else if (auth.userExist(username)) {
					httpRequest.setAttribute("msg", "This username has been used.");
					return "wrong";
				}
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
				mail.sendConfirmMail(email, OTP);
				final LocalDateTime expire = LocalDateTime.now(Clock.system(ZoneId.of("+8"))).plusMinutes(10);
				auth.createOTP(OTP, expire);
				this.registerCareTaker.addMemento(this.registerOriginator.saveToMemento());
				this.registerOriginator.setState(2);
				result = "next";
			}
		} else { // GET
			if (key == null) {
				result = "back";
			} else {
				this.setOrCt(key);
				if (this.registerOriginator.getState() > 1) {
					result = "next";
				} else if (this.registerOriginator.getState() < 1) {
					result = "back";
				} else {
					httpRequest.setAttribute("email", this.registerOriginator.getEmail());
					httpRequest.setAttribute("username", this.registerOriginator.getUsername());
					httpRequest.setAttribute("name", this.registerOriginator.getName());
					httpRequest.setAttribute("telephone", this.registerOriginator.getTelephone());
					httpRequest.setAttribute("address", this.registerOriginator.getAddress());
					result = "success";
				}
			}
		}
		return result;
	}
	
	public String toRegisterPassword() {
		this.orCtStored = OrCtStored.getInstance();
		String result = "error";
		HttpServletRequest httpRequest = ServletActionContext.getRequest();
		String method = (String) httpRequest.getMethod();
		String type = (String)httpRequest.getParameter("type");
		String key = this.auth.getOrCTID();
		if (method.equals("POST")) {
			this.setOrCt(key);
			if (type.equals("back")) {
				this.registerOriginator.restoreFromMemento(this.registerCareTaker.getLastMemento());
				result = "back";
			} else {
				String inputOTP = (String) httpRequest.getParameter("OTP");
				String password = (String) httpRequest.getParameter("password");
				String confirmPassword = (String) httpRequest.getParameter("confirmPassword");
				if(this.auth.checkOTP(inputOTP)) {
					if (password.equals("") || confirmPassword.equals("") || password.length() > 10 || confirmPassword.length() > 10) {
						httpRequest.setAttribute("msg", "Password & confirm password is required and must be under or equal than 10 characters long.");
						return "wrong";
					} else if (!password.equals(confirmPassword)) {
						httpRequest.setAttribute("msg", "Password & confirm password must be the same.");
						return "wrong";
					}
				} else {
					httpRequest.setAttribute("msg", "OTP code is incorrect.");
					return "wrong";
				}
				String email = this.registerOriginator.getEmail();
				String username = this.registerOriginator.getUsername();
				String name = this.registerOriginator.getName();
				String telephone = this.registerOriginator.getTelephone();
				String address = this.registerOriginator.getAddress();
				auth.createUser(email, username, telephone, address, password, name);
				this.registerCareTaker = null;
				this.registerOriginator = null;
				this.orCtStored.removeOrCT(key);
				this.auth.clearOrCTID();
				result = "next";
			}
		} else { // GET
			if (key == null) {
				result = "back";
			} else {
				this.setOrCt(key);
				if (this.registerOriginator.getState() > 2) {
					result = "next";
				} else if (this.registerOriginator.getState() < 2) {
					result = "back";
				} else {
					result = "success";
				}
			}
		}
		return result;
	}
	
	public String toRegisterDone() {
		return "success";
	}
}