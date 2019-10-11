package com.models;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;
public class Mail {
	
	public void send(String address, String subject, String content) {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "webmail.yuntech.edu.tw");
		props.setProperty("mail.user", "b10623006");
		props.setProperty("mail.password", "kue930315");
  
		Session mailSession = Session.getDefaultInstance(props, null);
		try {
		Transport transport = mailSession.getTransport();
  
		MimeMessage message = new MimeMessage(mailSession);
		message.setSubject(subject);
		
			message.setContent("This is a test", "text/plain");
		
		message.addRecipient(Message.RecipientType.TO,
			 new InternetAddress(address));
  
		transport.connect();
		transport.sendMessage(message,
			message.getRecipients(Message.RecipientType.TO));
		transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendOTP(String address, String OTP) {
		this.send(address,"Transaction Verification code","Your Verification code is:"+ OTP);
		// send OTP code format
	}
}
