package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Auth;
import com.models.User;

public class CreateSessionNormalTest {
	Auth auth;
	HttpSession httpSession;	

	public Boolean userLogin(String userName, String userPass){
		httpSession = mock(HttpSession.class);
		auth = new Auth(httpSession);
		User user = new User();
		user.userName = userName;
		user.userPass = userPass;
		return auth.createSession(user);
    }

	@Test
    public void createSession_rightLoginData_returnTrue(){
		assertTrue(userLogin("u1", "u1"));
		assertTrue(userLogin("u2", "u2"));
    }

}
