package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.models.Auth;
import com.models.User;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;
public class AuthTest {
	Auth auth;
	@Before
	public void setup() {
		HttpSession httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
	}
	
	@Test
    public void createSession_returnTrue() throws Exception {
		User user = new User();
		user.userName = "u1";
		user.userPass = "u1";
		assertTrue(auth.createSession(user));
    }
	@Test
    public void createSession_returnFalse() throws Exception {
		User user = new User();
		user.userName = "u1";
		user.userPass = "u2";
		assertFalse(auth.createSession(user));
    }

}