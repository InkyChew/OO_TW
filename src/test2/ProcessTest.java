package test2;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Deposit;
import com.models.Payment;
import com.models.User;
import com.controller.GUI;

public class ProcessTest {
	HttpSession httpSession;
	GUI gui = new GUI(httpSession);
	Payment payment = new Payment(httpSession);
	Deposit deposit = new Deposit(httpSession);
	@Test
    public void process_hasUser_hasNoTrader(){
		String result = payment.process(1, 5, 100);
		assertEquals("error", result);
		
    }
	@Test
    public void process_hasNoUser_hasTrader(){
		String result = payment.process(5, 2, 100);
		assertEquals("error", result);
    }
	@Test
    public void process_hasNoUser_hasNoTrader(){
		String result = payment.process(6, 5, 100);
		assertEquals("error", result);
    }
	
	@Test
    public void process_hasBalance(){
		int userId = 1;
		int traderId = 2;
		User user = gui.getAuthUser(userId);
		int s = user.wallet.walletMoney;
		String result = "";
		if (user.wallet.walletMoney > 30) {
			result = payment.process(userId, traderId, user.wallet.walletMoney - 30, httpSession);
			deposit.process(userId, traderId, s);
			assertEquals("success", result);
		}
		assertNotEquals("", result);
    }
	@Test
    public void process_hasNoBalance(){
		int userId = 1;
		int traderId = 2;
		User user = gui.getAuthUser(userId);
		String result = "";
		result = payment.process(userId, traderId, user.wallet.walletMoney + 1);
		assertEquals("error", result);
    }
}
