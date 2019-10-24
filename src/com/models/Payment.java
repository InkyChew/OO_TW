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
			 
			 User user = gui.getUser(userId);
			 User trader = gui.getUser(traderId);
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
	        	if (balance >= (int)(amount + (fee * discount))) {
	    		    balance-=(int)(amount + (fee * discount));
	    		    user.wallet.walletMoney=balance;
	    		    // for trader
	    		    gui.receivement(traderId, userId, amount);
	    		    this.amount = (int)(amount + (fee * discount));
	    		    
	    		    setTransactionDetail(user.getWallet().getWalletId());
	    		    session.merge(user.getWallet());
	   	         	output="success";
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

