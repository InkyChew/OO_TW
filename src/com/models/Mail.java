package com.models;
import javax.mail.*;
import javax.mail.internet.*;

import java.util.Date;
import java.util.Properties;
public class Mail {
	
	public void send(String address, String subject, String content) {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "webmail.yuntech.edu.tw");
		props.setProperty("mail.user", "*****");//Sender account
		props.setProperty("mail.password", "*****");//Sender password
		Session mailSession = Session.getDefaultInstance(props, null);
		try {
		Transport transport = mailSession.getTransport();
  
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("None-Reply-epay@mail.epay.com"));
		message.setSubject(subject);
		message.setSentDate(new Date());
		message.setContent(content, "text/plain");
		message.addRecipient(Message.RecipientType.TO,
		    new InternetAddress(address));//Recipient account
  
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
		this.send(address,"Transaction Verification code","This is your tranction Verification code:"+ OTP + "\nPlease verify it in 10 minutes.\n\nHave a good day~ epay");
		// send OTP code format
	}
}
