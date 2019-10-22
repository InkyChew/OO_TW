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
//			 traderId = userId;
			 List data = session.createCriteria(User.class).add(Restrictions.eq("userId",userId)).list();
		     User user = null; 
//		     User trader = null;
		     if(data.size() > 0 ) {
	    	 	type = "deposit";
	        	user = (User) data.get(0);
//	        	trader = user;
	        	this.userId = userId;
	        	this.traderId = userId; // in deposit user = trader
	        	this.amount = amount;
	        	
	        	balance = user.getWallet().getWalletMoney();
	        	balance+=amount;
    		    user.getWallet().setWalletMoney(balance);
    		    
    		    setTransactionDetail(user.getWallet().getWalletId());
    		    tx = session.beginTransaction();
    		    session.merge(user);
		     }
	 		 data.clear();
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

