package test;

import static org.junit.Assert.*;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import com.models.Auth;
import com.models.HibernateUtil;
import com.models.User;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
public class AuthTest {
	Auth auth;
	HttpSession httpSession;
	String OTP;
	LocalDateTime expire;

    public Boolean userLogin(String userName, String userPass){
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		User user = new User();
		user.userName = userName;
		user.userPass = userPass;
		return auth.createSession(user);
    }
	public void setupOTP() {
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		for(int i = 0; i < 8; i++){
	      int random = (int)((Math.random() * 3) + 1);
	      if(random == 1){
	    	OTP += (char)(int)((Math.random()*10)+48);
	      }else if(random == 2){
	        OTP += (char)(int)(((Math.random()*26) + 65));
	      }else{
	        OTP += (char)(int)((Math.random()*26) + 97);
	      }
	    }
		expire = LocalDateTime.now(Clock.system(ZoneId.of("+8"))).plusMinutes(10);
		auth.createOTP(OTP, expire);
	}
	
	@Test
    public void createSession_rightLoginData_returnTrue(){
		assertTrue(userLogin("u1", "u1"));
    }
	@Test
    public void createSession_wrongLoginData_returnFalse(){
		assertFalse(userLogin("u1", "u2"));
    }
	@Test
    public void checkSession_hasUser_returnTrue(){
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("userId")).thenReturn(1);
		auth = Auth.getInstance(httpSession);
		assertTrue(auth.checkSession());
    }
	@Test
    public void checkSession_hasNoUser_returnFalse(){
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("userId")).thenReturn(null);
		auth = Auth.getInstance(httpSession);
		assertFalse(auth.checkSession());
    }
	@Test
    public void removeSession_returnVoid() throws Exception {
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		auth.removeSession();
    }
	@Test
    public void getUserId_hasUserId_returnInt(){
		userLogin("u1", "u1");
		when(httpSession.getAttribute("userId")).thenReturn(1);
		assertEquals(1, auth.getUserId());
    }
	@Test
    public void getUserId_hasNoUserId_returnNull() {
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("userId")).thenReturn(null);
		auth = Auth.getInstance(httpSession);
		auth.removeSession();
		assertEquals(0, auth.getUserId());
    }

	@Test
    public void createOTP_hasUserId_returnVoid() throws Exception {
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		for(int i = 0; i < 8; i++){
	      int random = (int)((Math.random() * 3) + 1);
	      if(random == 1){
	    	OTP += (char)(int)((Math.random()*10)+48);
	      }else if(random == 2){
	        OTP += (char)(int)(((Math.random()*26) + 65));
	      }else{
	        OTP += (char)(int)((Math.random()*26) + 97);
	      }
	    }
		expire = LocalDateTime.now(Clock.system(ZoneId.of("+8"))).plusMinutes(10);
		auth.createOTP(OTP, expire);
    }

	@Test
    public void checkOTP_correctOTP_returnTrue(){
		setupOTP();
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("OTP")).thenReturn(OTP);
		when(httpSession.getAttribute("OTPExpire")).thenReturn(expire);
		auth = Auth.getInstance(httpSession);
		assertTrue(auth.checkOTP(OTP));
		
    }
	@Test
    public void checkOTP_wrongOTP_returnFalse() {
		setupOTP();
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("OTP")).thenReturn(OTP);
		when(httpSession.getAttribute("OTPExpire")).thenReturn(expire);
		auth = Auth.getInstance(httpSession);
		assertFalse(auth.checkOTP(""));
    }
	
	@Test
    public void isAdmin_isAdmin_returnTrue(){
		userLogin("admin", "admin");
		assertTrue(auth.isAdmin());
    }
	@Test
    public void isAdmin_isNotAdmin_returnFalse() {
		userLogin("u1", "u1");
		assertFalse(auth.isAdmin());
    }
	@Test
    public void getUser_existedUser_returnUser(){
		int userId = 1;
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		assertNotEquals(null,auth.getUser(userId));
    }
	@Test
    public void getUser_notExistedUser_returnNull() {
		int userId = 0;
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		assertEquals(null,auth.getUser(userId));
    }
	@Test
    public void getCurrentUser_hasUserId_returnUser(){
		userLogin("u1", "u1");
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("userId")).thenReturn(1);
		auth = Auth.getInstance(httpSession);
		assertNotEquals(null,auth.getCurrentUser());
    }
	@Test
    public void getCurrentUser_hasNoUserId_returnNull() {
		httpSession = mock(HttpSession.class);
		when(httpSession.getAttribute("userId")).thenReturn(null);
		auth = Auth.getInstance(httpSession);
		auth.removeSession();
		assertEquals(null,auth.getCurrentUser());
    }@Test
    public void readClientAll_hasUser_returnListNotEmpty() {
		httpSession = mock(HttpSession.class);
		auth = Auth.getInstance(httpSession);
		assertNotEquals(0,auth.readClientAll().size());
    }
}
