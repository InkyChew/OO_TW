package com.models;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class FailTimeHandler extends LoginHandler {	
	FailTimeHandler(LoginHandler s){
		super(s);
	}
	public boolean canHandle() {
		if(failTimes > 3) {
			return true;
		} else {
			return false;
		}
	}
	public void handleRequest() {
		if(canHandle()) {
			setFailTimes(failTimes); 
			lock(); // lock
		} else {
			super.handleRequest();
		}
	}
	public void lock() {
		final LocalDateTime expire = LocalDateTime.now(Clock.system(ZoneId.of("+8"))).plusMinutes(10);
		httpSession.setAttribute("failTimesExpire", expire);
	}
}
