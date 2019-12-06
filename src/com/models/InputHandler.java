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
		super.handleTemplate("Input cannot more than 10.");
	}
	public boolean checkStr(String str) {
		boolean result = false;
		if (str != null && str.length() > 0 && str.length() <= 10) {
			result = true;
		}
		return result;
	}
}
