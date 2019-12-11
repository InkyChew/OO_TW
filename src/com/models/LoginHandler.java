package com.models;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

public abstract class LoginHandler{
	static HttpSession httpSession = ServletActionContext.getRequest().getSession();
	LoginHandler successor;
	protected User user;
	protected User newUser;
	int failTimes = this.getFailTimes();
	
	LoginHandler(LoginHandler s){
        this.successor = s;
        HttpServletRequest httpServletRequest = ServletActionContext.getRequest();
        user = new User();
        user.userName = (String) httpServletRequest.getAttribute("user.userName");
        user.userPass = (String) httpServletRequest.getAttribute("user.userPass");
    }
	public void handleRequest(){
        if(successor != null){
            successor.handleRequest();
        }else{
        	httpSession = ServletActionContext.getRequest().getSession();
        	httpSession.setAttribute("userId", newUser.userId);
	   		user = newUser;
	   		failTimes = 0;
	   		setFailTimes(failTimes);
            System.out.println("login success.");
        }
    }
	public int getFailTimes() {
		Object x = httpSession.getAttribute("failTimes");
		Object y = httpSession.getAttribute("failTimesExpire");
		if(httpSession.getAttribute("failTimes") == null || httpSession.getAttribute("failTimesExpire") == null) {
			 return 0;
		 } else {
			 failTimes = (int) httpSession.getAttribute("failTimes");
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
		final LocalDateTime expire = LocalDateTime.now(Clock.system(ZoneId.of("+8"))).plusMinutes(10);
		httpSession.setAttribute("failTimesExpire", expire);
		int x = (int) httpSession.getAttribute("failTimes");
		x = 0;
	}
	public void setErrorMsg(String error) {
		httpSession.setAttribute("errorMsg", error);
	}
    public abstract boolean canHandle();
}