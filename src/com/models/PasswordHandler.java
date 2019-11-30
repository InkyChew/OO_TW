package com.models;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

public class PasswordHandler extends LoginHandler {
	Session session = HibernateUtil.getSessionFactory().openSession();
	PasswordHandler(LoginHandler s){
		super(s);
	}
	public boolean canHandle() {
		List<User> data = session.createCriteria(User.class).add(Restrictions.eq("userName", user.userName)).list();
		newUser = (User) data.get(0);
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
			setErrorMsg("Password is wrong.");
		} else {
			super.handleRequest();
		}
	}
}
