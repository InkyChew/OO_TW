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

public class CreateOTPTest {
	Auth auth;
	HttpSession httpSession;
	String OTP;
	LocalDateTime expire;

	@Test
    public void createOTP() throws Exception {
		httpSession = mock(HttpSession.class);
		auth = new Auth(httpSession);
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

}
