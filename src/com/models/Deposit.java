package com.models;

import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.models.AbTransfer;
import com.models.Deposit;

public class Deposit extends Transfer implements ProcessAPI{
	// controller
	@Override
	public String process(int userId, int traderId, int amount){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String output="error";
		try{
			 System.out.println(userId);
			 User user = gui.getUser(userId);
			 if(user != null) {
	    	 	type = "deposit";
	        	this.userId = userId;
	        	this.traderId = userId; // in deposit user = trader
	        	this.amount = amount;
	        	
	        	balance = user.getWallet().getWalletMoney();
	        	balance+=amount;
    		    user.getWallet().setWalletMoney(balance);
    		    
    		    setTransactionDetail(user.getWallet().getWalletId());
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

