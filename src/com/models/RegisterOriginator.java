package com.models;

public class RegisterOriginator {
	
	private String pass;
	private RegisterMemento registerMemento;
	RegisterState registerState = new RegisterState();
	
	private boolean contract;
	private String name;
	private String telephone;
	private String address;
	private String email;
	private String username;
	private String password;
	private int state;
	
	public RegisterOriginator(String pass) {
		this.setPass(pass);
		this.setContract(false);
		this.setState(0);
	}
	
	public RegisterMemento saveToMemento() {
		this.registerMemento = new RegisterMemento();
		RegisterState registerState = (RegisterState) this.registerState.clone();
		registerState.setAddress(address);
		registerState.setContract(contract);
		registerState.setEmail(email);
		registerState.setName(name);
		registerState.setPassword(password);
		registerState.setState(state);
		registerState.setTelephone(telephone);
		registerState.setUsername(username);
		this.registerMemento.setRegisterState(registerState, pass);
		return registerMemento;
	}
	
	public void restoreFromMemento(RegisterMemento m) throws IllegalAccessException {
		RegisterState registerState = m.getRegisterState(pass);
		this.setValue(registerState);
	}
	
	public void setValue(RegisterState registerState) {
		this.setAddress(registerState.getAddress());
		this.setContract(registerState.getContract());
		this.setEmail(registerState.getEmail());
		this.setName(registerState.getName());
		this.setPassword(registerState.getPassword());
		this.setTelephone(registerState.getTelephone());
		this.setUsername(registerState.getUsername());
		this.setState(registerState.getState());
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @param contract the contract to set
	 */
	public void setContract(boolean contract) {
		this.contract = contract;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	public int getState() {
		return state;
	}
	/**
	 * @return the contract
	 */
	public boolean getContract() {
		return contract;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
}
