package com.models;

public class AbTransfer {
	   private int userId;
	   private int traderId;
	   private int amount; 
	   private ProcessAPI processAPI;
	   
	   public AbTransfer(int userId, int traderId, int amount, ProcessAPI processAPI) {
	      this.processAPI = processAPI;
	      this.userId = userId;  
	      this.traderId = traderId;  
	      this.amount = amount;
	   }
	 
	   public String process() {
	      return processAPI.process(userId, traderId, amount, processAPI);
	   }
}

