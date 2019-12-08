package com.models;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransactionDetail {
	public int id;
	public int walletId;
	public String date;
	public String type;
	public int amount;
	public int balance;
	public int traderId;
	public void setId(int id){
	    this.id=id;
	}
	public int getId(){
	    return id;
	}
	public void setWalletId(int walletId){
	    this.walletId=walletId;
	}
	public int getWalletId(){
	    return walletId;
	}
	public void setDate(String date){
	    this.date=date;
	}
	public String getDate(){
	    return date;
	}
	public void setType(String type){
	    this.type=type;
	}
	public String getType(){
	    return type;
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
	public String updateTransactionDetail() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		String output="error";
		Transaction tx = session.beginTransaction();
		try{
			session.merge(this);
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

