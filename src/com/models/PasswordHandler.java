package com.models;

public class PasswordHandler extends LoginHandler {
	PasswordHandler(LoginHandler s){
		super(s);
	}
	public boolean canHandle() {
		if(!(newUser.userPass.equals(user.userPass))) {
			return true;
		} else {
			return false;
		}
	}
	public void handleRequest() {
		if(canHandle()) {
			failTimes += 1;
			setFailTimes(failTimes);
		} else {
			super.handleRequest();
		}
	}
}
