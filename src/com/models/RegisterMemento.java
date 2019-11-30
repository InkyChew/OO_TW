package com.models;

public class RegisterMemento {

	private boolean contract;
	private String name;
	private String telephone;
	private String address;
	private String email;
	private String username;
	private String password;
	private int state;
	
	public RegisterMemento(boolean contract, String name, String telephone,
			String address, String email, String username, String password, int state) {
		this.setContract(contract);
		this.setAddress(address);
		this.setEmail(email);
		this.setName(name);
		this.setTelephone(telephone);
		this.setUsername(username);
		this.setPassword(password);
		this.setState(state);
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
