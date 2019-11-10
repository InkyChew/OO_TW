package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Auth;

public class GetUserIdHasNoUserIdTest {
	Auth auth;
	HttpSession httpSession;	

	@Test
    public void getUserId_hasNoUserId_returnNull() {
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("userId")).thenReturn(null);
		auth = new Auth(httpSession);
		auth.removeSession();
		assertEquals(0, auth.getUserId());
    }

}
