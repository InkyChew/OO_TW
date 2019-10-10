package com.models;

public class UserInfo {
	public int id;
	public String name;
	public String telephone;
	public String address;
	public String email;
	public void setId(int id){
	    this.id=id;
	}
	public int getId(){
	    return id;
	}
	public void setName(String name){
	    this.name=name;
	}
	public String getName(){
	    return name;
	}
	public void setTelephone(String telephone){
	    this.telephone=telephone;
	}
	public String getTelephone(){
	    return telephone;
	}
	public void setAddress(String address){
	    this.address=address;
	}
	public String getAddress(){
	    return address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}

