package test2;

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
	ProcessTest.class,
})

public class PaymentTests {

}
