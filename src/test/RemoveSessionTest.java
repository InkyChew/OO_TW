package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Auth;
import com.models.User;

public class RemoveSessionTest {
	Auth auth;
	HttpSession httpSession;	

	@Test
    public void removeSession_returnVoid() throws Exception {
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		auth.removeSession();
    }

}
