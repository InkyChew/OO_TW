package com.models;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;

public abstract class LoginHandler{
	static HttpSession httpSession = ServletActionContext.getRequest().getSession();
	LoginHandler successor;
	protected User user;
	protected User newUser; // get from db
	int failTimes = this.getFailTimes();
	
	LoginHandler(LoginHandler s){
        this.successor = s;
    }
	public void handleRequest(){
        if(successor != null){
            successor.handleRequest();
        }else{
        	httpSession.setAttribute("userId", newUser.userId);
	   		user = newUser;
//	   		auth = true;
	   		failTimes = 0;
	   		setFailTimes(failTimes);
            System.out.println("login success.");
        }
    }
	public int getFailTimes() {
		if(httpSession.getAttribute("failTimes") == null || httpSession.getAttribute("failTimesExpire") == null) {
			 return 0;
		 } else {
			 int failTimes = (int) httpSession.getAttribute("failTimes");
			 LocalDateTime expire = (LocalDateTime) httpSession.getAttribute("failTimesExpire");
			 final LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of("+8")));
				if(now.isBefore(expire)) {
					return failTimes;
				}
			 return 0;
		 }
	}
	public void setFailTimes(int failTimes) {
		httpSession.setAttribute("failTimes", failTimes);
	}
    public abstract boolean canHandle();
}