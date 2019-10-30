package test;

import static org.junit.Assert.*;

import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.models.Auth;
import com.models.User;

import org.junit.Test;
import org.junit.Before;

public class AuthTest {
	Auth auth = Auth.getInstance();
	
//	@Before public void beforeTest() { // when login
//	  System.out.println("Before");
//	  auth.createSession(User user);
//	 }
//	@Test
//	public void checkSession_Login_returnTrue() {
//		assertFalse(auth.checkSession());
//	}
	@Before public void beforeTest() { // when login
		request.getRequestURL("http://localhost:8080/test/");
	}
	@Test
	public void checkSession_noLogin_returnFalse() {
		assertFalse(auth.checkSession());
	}
	
}
