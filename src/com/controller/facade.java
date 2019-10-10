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
import com.models.Payment;
import com.models.Auth;
import com.models.Admin;

public class facade { // 你就是個controller
	private User user = new User();
	private Transfer transfer;
	private TransactionDetail transactionDetail;
	private List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();
	
	Auth auth;
	Admin admin;
	Mail mail = new Mail();
	
//	facade() {
//		auth = new Auth();
//		mediator = new controller();
//	}
	
	public String login() {
		String output = "error";
		if(auth.createSession(user)) {
			if(auth.isAdmin(user)) {
				// account detail page
		        output = admin.readClientAll();
			}else {
				output = "success"; // menu
			}
		} else {
			output = "error"; // error page
		}
		return output;
	}

	public String showPayOTPView() { // 付款人
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
			// 記錄使用者輸入的值
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
	
	public String checkTransactionHistory() {
		String output = "error";
		if(auth.checkSession()) {
			output = user.showTransaction(); // transaction detail page
		} else {
			output = "error"; // error page
		}
		return output;
	}
	
	public String logout() {
		auth.removeSession(user.userId);
		String output="success";
		return output; // login page
	}	
}