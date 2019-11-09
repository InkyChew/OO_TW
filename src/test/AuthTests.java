package test;

import static org.mockito.Mockito.mock;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpSession;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.models.Auth;
import com.models.User;

@RunWith(Suite.class)

@SuiteClasses({ 
	// login_fail
	CreateSessionWrongTest.class,
	// login_normal_user
	CreateSessionNormalTest.class,
	CheckSessionHasUserTest.class,
	GetUserIdHasUserIdTest.class,
	GetUserExistedUserTest.class,
	GetCurrentUserHasUserTest.class,
	IsNotAdminTest.class,
	CreateOTPTest.class,
	CheckOTPTest.class,
	
	// logout
	RemoveSessionTest.class,
	CheckSessionHasNoUserTest.class,
	GetUserIdHasNoUserIdTest.class,
	GetUserNotExistedUserTest.class,
	GetCurrentUserHasNoUserTest.class,
	
	//login_admin_user
	CreateSessionAdminTest.class,
	IsAdminTest.class
})

public class AuthTests {

}
