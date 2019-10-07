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
import java.text.DateFormat;
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
import com.models.Auth;
import com.models.Admin;

public class facade { // 你就是個controller
	private User user = new User();
	private Transfer transfer;
	private TransactionDetail transactionDetail;
	private List<TransactionDetail> transactionDetails = new ArrayList<TransactionDetail>();
	
	Auth auth;
	Admin admin;
	
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

	public void showPayOTPView() { // 付款人
		if(auth.checkSession()) {
			// send otp
			// show pay+otp view
		} else {
			// error page
		}
	}
	public void transactionSuccessView() {
		if(auth.checkSession()) {
			// 記錄使用者輸入的值
			int traderId=transfer.traderId;
			int amount= transfer.amount;
			transfer= new Payment();
			transfer.setAmount(amount);
			transfer.setTraderId(traderId);
			
			if(check otp) {
				// transaction
				// record transaction payment&receivement
				String output = transfer.process();
				
				// transaction success+transaction detail
				if (output=="success") {
					output = toPlatform();
				}
				return output;
			} else {
				// error page
			}
		} else {
			// error page
		}
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