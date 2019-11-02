package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Auth;

public class GetCurrentUserHasNoUserIdTest {
	Auth auth;
	HttpSession httpSession;

	@Test
    public void getCurrentUser_hasNoUserId_returnNull() {
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("userId")).thenReturn(null);
		auth = Auth.getInstance(httpSession);
		auth.removeSession();
		assertEquals(null,auth.getCurrentUser());
    }

}
