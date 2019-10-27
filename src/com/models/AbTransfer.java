package com.models;

public class AbTransfer {
	   public int userId;
	   public int traderId;
	   public int amount; 
	   public ProcessAPI processAPI;
	   
	   public AbTransfer(int userId, int traderId, int amount, ProcessAPI processAPI) {
	      this.processAPI = processAPI;
	      this.userId = userId;  
	      this.traderId = traderId;  
	      this.amount = amount;
	   }
	 
	   public String process() {
	      return processAPI.process(userId, traderId, amount);
	   }
}

