package com.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.controller.GUI;
import com.models.TransactionDetail;
public class Transfer {
	public int amount;
	public int balance;
	public int userId;
	public int traderId;
	public String otp;
	public String type;
	
	public Discounter levelDiscounter; // context has strategy
	public final int fee = 30;
	public GUI gui;
	public TransactionDetail transactionDetail;
	public Transfer(){
		gui = new GUI(); // mediator
	}
	// for testing
	public Transfer(HttpSession hs){
		gui = new GUI(hs); // mediator
	}
	public void setAmount(int amount){
	    this.amount=amount;
	}
	public int getAmount(){
	    return amount;
	}
	public void setBalance(int balance){
	    this.balance=balance;
	}
	public int getBalance(){
	   return balance;
	}
	public void setTraderId(int traderId){
	    this.traderId=traderId;
	}
	public int getTraderId(){
	   return traderId;
	}
	public void setUserId(int userId){
	    this.userId=userId;
	}
	public int getUserId(){
	   return userId;
	}
	public void setType(String type){
	    this.type=type;
	}
	public String getType(){
	   return type;
	}
	public void setDiscounter(Discounter lev){
		this.levelDiscounter = lev;
	}
	public double getDiscount(){
		return levelDiscounter.getDiscount(amount);
	}
	public void setOtp(String otp){
	    this.otp=otp;
	}
	public String getOtp(){
	   return otp;
	}
	
	public String process() {
		return "success";
	}
	public String setTransactionDetail(int walletId) {
		return gui.setTransferDetail(this, walletId);
	}
}

