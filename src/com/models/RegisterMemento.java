package com.models;

public class RegisterMemento {
	
	private RegisterState registerState;
	private int pass;
	
	public RegisterMemento() {}
	
	public void setRegisterState(RegisterState registerState, String pass) {
		this.registerState = registerState;
		this.pass = pass.hashCode();
	}
	
	public RegisterState getRegisterState(String pass) throws IllegalAccessException {
		int h = pass.hashCode();
		if (h == this.pass) {
			return this.registerState;
		} else {
			throw new IllegalAccessException();
		}
	}
}
