package test2;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.models.Payment;
import com.models.User;
import com.controller.GUI;

public class ProcessTest {
	HttpSession httpSession;
	GUI gui = new GUI();
	@Test
    public void process_hasUser_hasNoTrader(){
		Payment payment = new Payment();
		String result = payment.process(1, 5, 100);
		assertEquals("error", result);
		
    }
	@Test
    public void process_hasNoUser_hasTrader(){
		Payment payment = new Payment();
		String result = payment.process(5, 2, 100);
		assertEquals("error", result);
    }
	@Test
    public void process_hasNoUser_hasNoTrader(){
		Payment payment = new Payment();
		String result = payment.process(6, 5, 100);
		assertEquals("error", result);
    }
	
	@Test
    public void process_hasBalance(){
		Payment payment = new Payment();
		int userId = 1;
		int traderId = 2;
		User user = gui.getAuthUser(userId);
		String result = "";
		if (user.wallet.walletMoney > 0) {
			result = payment.process(userId, traderId, user.wallet.walletMoney);
			assertEquals("success", result);
		}
		assertNotEquals("", result);
    }
	@Test
    public void process_hasNoBalance(){
		Payment payment = new Payment();
		int userId = 1;
		int traderId = 2;
		User user = gui.getAuthUser(userId);
		String result = "";
		if (user.wallet.walletMoney > 0) {
			result = payment.process(userId, traderId, user.wallet.walletMoney + 1);
			assertEquals("success", result);
		}
		assertNotEquals("", result);
    }
}
