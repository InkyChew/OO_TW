package com.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.controller.controller;
import com.models.TransactionDetail;
public class Transfer {
	public int amount;
	public int balance;
	public int userId;
	public int traderId;
	public String type;
	public controller mediator;
	
	public TransactionDetail transactionDetail;
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
	
	public String process() {
		return "success";
	}
	public String setTransactionDetail(int walletId) { // 跟 controller 說叫 transaction detail 做事
		mediator.SetTransferTransactionDetail(amount, balance, type, walletId, traderId);
		transactionDetail = new TransactionDetail();
		// transactionDetail.setDate(date);
		// transactionDetail.setAmount(amount);
		// transactionDetail.setBalance(balance);
		// transactionDetail.setType(type);
		// transactionDetail.setWalletId(walletId);
		// transactionDetail.setTraderId(traderId);
		return updateTransactionDetail();
	}
	public String updateTransactionDetail() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String output="error";
		Transaction tx = session.beginTransaction();
		try{
			session.merge(transactionDetail);
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
}

