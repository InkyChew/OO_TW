package test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Auth;
import com.models.User;

public class GetUserExistedUserTest {
	Auth auth;
	HttpSession httpSession;	

	@Test
    public void getUser_existedUser_returnUser(){
		int userId = 1;
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		assertNotEquals(null,auth.getUser(userId));
    }

}
