package com.sharad.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender emailSender;

	@Override
	public String sendSimpleEmail(String to, String subject, String body) {
		SimpleMailMessage message= new SimpleMailMessage();
		message.setFrom("sharad.kumar15024@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		
		emailSender.send(message);
		return "Mail Sent Successfully...";
		
	}

}
