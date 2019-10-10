package com.models;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.models.Role;
import com.models.Wallet;
import com.models.UserInfo;
public class User {
	public int userId;
	public UserInfo userInfo;
	public Wallet wallet;
	public Role userRole;
	public String userName;
	public String userPass;
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
}

