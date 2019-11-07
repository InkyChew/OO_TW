package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Auth;
import com.models.User;

public class CheckSessionHasUserTest {
	Auth auth;
	HttpSession httpSession;

	@Test
    public void checkSession_hasUser_returnTrue(){
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("userId")).thenReturn(1);
		auth = new Auth(httpSession);
		assertTrue(auth.checkSession());
    }

}
