package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Auth;

public class GetUserNotExistedUserTest {
	Auth auth;
	HttpSession httpSession;
	
	@Test
    public void getUser_notExistedUser_returnNull() {
		int userId = 0;
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		assertEquals(null,auth.getUser(userId));
    }

}
