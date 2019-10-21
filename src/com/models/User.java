package com.models;

import com.models.Role;
import com.models.Wallet;
import com.models.UserInfo;
import com.models.Discounter;

public class User {
	public int userId;
	public UserInfo userInfo;
	public Wallet wallet;
	public Role userRole;
	public String userName;
	public String userPass;
	public int userLevel; // 0-no 1-VIP 2-VVIP
	public Discounter levelDiscounter; // context has strategy
	public double discount;
	
	public void setUserId(int userId){
	    this.userId=userId;
	}
	public int getUserId(){
	    return userId;
	}
	public void setUserInfo(UserInfo userInfo){
	    this.userInfo=userInfo;
	}
	public UserInfo getUserInfo(){
	    return userInfo;
	}
	public void setWallet(Wallet wallet){
	    this.wallet=wallet;
	}
	public Wallet getWallet(){
	    return wallet;
	}
	public void setUserRole(Role userRole){
	    this.userRole=userRole;
	}
	public Role getUserRole(){
	    return userRole;
	}
	public void setUserName(String userName){
	    this.userName=userName;
	}
	public String getUserName(){
	    return userName;
	}
	public void setUserPass(String userPass){
	    this.userPass=userPass;
	}
	public String getUserPass(){
	    return userPass;
	}
	public void setUserLevel(int userlevel){ // DB
		this.userLevel = userlevel;
	}
	public int getUserLevel(){ // DB
		return userLevel;
	}
	public void setUserLevel(Discounter lev){
		this.levelDiscounter = lev;
	}
	public double getDiscount(){
		return levelDiscounter.getDiscount(discount);
	}
}

