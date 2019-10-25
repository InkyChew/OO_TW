package com.models;

import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.models.Receivement;

public class Payment extends Transfer implements ProcessAPI{

	public String process(int userId, int traderId, int amount) {
		// for DB
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String output="error";
		
		try{			 
			 User user = gui.getAuthUser(userId);
			 User trader = gui.getAuthUser(traderId);
		     if(user != null && trader != null) {
	        	type = "payment";
	        	this.amount = amount;
	        	this.traderId = traderId;
	        	this.userId = userId;
	        	
	        	balance = user.getWallet().getWalletMoney();
	        	
	        	// discounter
	        	int level = user.getUserLevel();
	        	DiscounterFactory UserDiscountFactory = new UserDiscountFactory(level);
	        	Discounter userLevel = UserDiscountFactory.createLevelDiscounter();
	        	setDiscounter(userLevel);
	        	double discount = getDiscount();
	        	
   			 	tx = session.beginTransaction();
   			 	System.out.println(amount);
   			 	int totalAmount = (int)(amount + (fee * discount));
	        	if (balance >= totalAmount) {
	        		System.out.println("pay");	
	    		    balance-=totalAmount;	    		    
	    		    user.wallet.walletMoney=balance;
	    		    // for trader
	    		    gui.receivement(traderId, userId, amount);
	    		    this.amount = totalAmount;
	    		    
	    		    setTransactionDetail(user.getWallet().getWalletId());
	    		    session.merge(user.getWallet());
	   	         	output="success";
	   	         	
	   	         	System.out.println("fee:" + fee);
	        		System.out.println("get discount:" + discount);
	        		System.out.println("total:" + totalAmount);
	        		System.out.println("balance:" + balance);
	    		}
		     }
	 		 // for DB
	 		 tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
		return output;
	}
}

