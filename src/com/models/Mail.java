package com.models;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;
public class Mail {
	
	public void send(String address, String subject, String content) {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "webmail.yuntech.edu.tw");
		//props.setProperty("mail.user", "b106230**");//Sender account
		//props.setProperty("mail.password", "*******");//Sender password
  
		Session mailSession = Session.getDefaultInstance(props, null);
		Transport transport = mailSession.getTransport();
  
		MimeMessage message = new MimeMessage(mailSession);
		message.setSubject(subject);
		message.setContent(content);
		message.addRecipient(Message.RecipientType.TO,
			 new InternetAddress(address));//Recipient account
  
		transport.connect();
		transport.sendMessage(message,
			message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}
	
	public void sendOTP(String address, String OTP) {
		this.send(address,"Transaction Verification code","Your Verification code:"+ OTP);
		// send OTP code format
	}
}
