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
		HttpSession httpSession = ServletActionContext.getRequest().getSession();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		String output="error";
		try{
//			 userId = (int) httpSession.getAttribute("userId");
			 
			 // DB reaction
			 List data = session.createCriteria(User.class).add(Restrictions.eq("userId",userId)).list();
			 List data2 = session.createCriteria(User.class).add(Restrictions.eq("userId",traderId)).list();
			 System.out.println(userId);
			 System.out.println(traderId);
			 System.out.println(amount);
			 System.out.println(data.size());
			 System.out.println(data2.size());
		     User user = null; 
		     User trader = null;
		     if(data.size() > 0 && data2.size() > 0) {
	        	user = (User) data.get(0);
	        	trader = (User) data2.get(0);
	        	balance = user.getWallet().getWalletMoney();
	        	type = "payment";
	        	
	        	// discounter
	        	int level = user.getUserLevel();
	        	DiscounterFactory UserDiscountFactory = new UserDiscountFactory(level);
	        	Discounter userLevel = UserDiscountFactory.createLevelDiscounter();
	        	setDiscounter(userLevel);
	        	double discount = getDiscount();
	        	
   			 	tx = session.beginTransaction();
	        	if (balance >= amount) {
	        		amount = (int)(amount + (fee * discount));
	    		    balance-=amount;
	    		    user.wallet.walletMoney=balance;
	    		    // for trader
	    		    Receivement receivement = new Receivement();
	    		    receivement.setTraderId(userId);
	    		    receivement.setUserId(traderId);
	    		    receivement.setAmount(amount);
	    		    if (! receivement.process().equals("success")) {
	    		    	return output;
	    		    }
	    		    setTransactionDetail(user.getWallet().getWalletId());
	    		    session.merge(user);
	   	         	output="success";
	    		}
		     }
	 		 data.clear();
	 		 data2.clear();
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

