package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Auth;
import com.models.User;

public class GetCurrentUserHasUserTest {
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
    public void getCurrentUser_hasUserId_returnUser(){
		userLogin("u1", "u1");
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("userId")).thenReturn(1);
		auth = new Auth(httpSession);
		assertNotEquals(null,auth.getCurrentUser());
    }

}
