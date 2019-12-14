package com.models;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class FailTimeHandler extends LoginHandler {	
	FailTimeHandler(LoginHandler s){
		super(s);
	}
	public boolean canHandle() {
		failTimes = this.getFailTimes();
		if(failTimes >= 3) {
			return true;
		} else {
			return false;
		}
	}
	public void handleRequest() {
		if(canHandle()) {
			lock();
			setFailTimes(failTimes);
			setErrorMsg("Fail more than 3 times. Lock for 10 mins.");
		} else {
			super.handleRequest();
		}
	}
	public void lock() { // Lock
		final LocalDateTime expire = LocalDateTime.now(Clock.system(ZoneId.of("+8"))).plusMinutes(10);
		httpSession.setAttribute("failTimesExpire", expire);
	}
}
