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

public class CreateSessionWrongTest {
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
    public void createSession_wrongLoginPassword_returnFalse(){
		assertFalse(userLogin("u1", "u2"));
    }
	@Test
    public void createSession_wrongLoginAccount_returnFalse(){
		assertFalse(userLogin("u6", "u6"));
    }

}
