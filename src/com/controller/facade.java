public class facade {
	controller mediator;
	Facade() {
		mediator = new controller();
	}
	// login
	// readClientAll
	// payment
	// showTransaction
	// toPlatform back_btn
	// QRpay
	public String readClientAll() {
		return mediator.readClientAll();
	}
	public String login() {
		return mediator.login();
	}
	public String payment() {
		return mediator.payment();
	}
	public String QRpay() {
		return mediator.QRpay();
	}
	public String showTransaction() {
		return mediator.showTransaction();
	}
	public String toPlatform() {
		return mediator.toPlatform();
	}
}