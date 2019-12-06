package com.models;

import org.apache.struts2.ServletActionContext;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.models.Receivement;

public class Receivement extends Transfer implements ProcessAPI{
	// controller
	
	public Receivement() {
		super();
	}
	// for testing
	public Receivement(HttpSession hs) {
		super(hs);
	}
	@Override
	public String process(int userId, int traderId, int amount){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String output="error";
		
		try{
			User user = gui.getAuthUser(userId);
			User trader = gui.getAuthUser(traderId);
			this.amount = amount;
			this.traderId = traderId;
			this.userId = userId;

		     if(user != null && trader != null) {
		    	 	type = "receivement";
		        	balance = user.getWallet().getWalletMoney();
		        	balance+=amount;
	    		    user.wallet.walletMoney=balance;
	    		    setTransactionDetail(user.getWallet().getWalletId());
	    		    tx = session.beginTransaction();
	    		    session.merge(user.getWallet());
		     }
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
	
	// for testing
	public String process(int userId, int traderId, int amount, HttpSession hs){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String output="error";
		
		try{
			User user = gui.getAuthUser(userId);
			User trader = gui.getAuthUser(traderId);
			this.amount = amount;
			this.traderId = traderId;
			this.userId = userId;

		     if(user != null && trader != null) {
		    	 	type = "receivement";
		        	balance = user.getWallet().getWalletMoney();
		        	balance+=amount;
	    		    user.wallet.walletMoney=balance;
	    		    setTransactionDetail(user.getWallet().getWalletId());
	    		    tx = session.beginTransaction();
	    		    session.merge(user.getWallet());
		     }
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

