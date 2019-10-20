package com.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
	public String type;
	
	public GUI gui;
//	public TransactionDetail transactionDetail;
	
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
	public String setTransactionDetail(int walletId) { // transaction detail
		DateFormat dfcurrentTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dfcurrentTime.format(new java.util.Date());
		this.gui = new GUI();
		return gui.setTransferDetail(this, walletId);
//		transactionDetail = new TransactionDetail();
//		transactionDetail.setDate(date);
//		transactionDetail.setAmount(amount);
//		transactionDetail.setBalance(balance);
//		transactionDetail.setType(type);
//		transactionDetail.setWalletId(walletId);
//		transactionDetail.setTraderId(traderId);
//		return updateTransactionDetail();
	}
	public String updateTransactionDetail(TransactionDetail transactionDetail) {
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

