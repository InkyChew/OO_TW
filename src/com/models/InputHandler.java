package com.models;

public class InputHandler extends LoginHandler {
	InputHandler(LoginHandler s){
		super(s);
	}
	public boolean canHandle() {
		if(!(checkStr(user.userPass) && checkStr(user.userName))) {
			return true;
		} else {
			return false;
		}
	}
	public void handleRequest() {
		if(canHandle()) {
			failTimes = this.getFailTimes();
			failTimes += 1;
			setFailTimes(failTimes);
			setErrorMsg("Input cannot more than 10.");
		} else {
			super.handleRequest();
		}
	}
	public boolean checkStr(String str) {
		boolean result = false;
		if (str != null && str.length() > 0 && str.length() <= 10) {
			result = true;
		}
		return result;
	}
}
