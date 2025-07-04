package com.sharad.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sharad.service.EmailService;

@Component
public class EmailRunner implements CommandLineRunner {
	
	@Autowired
	private EmailService emailService;

	@Override
	public void run(String... args) throws Exception {
		try {
			emailService.sendSimpleEmail("sharadk4545@gmail.com",
																"Test Mail from Springboot ",
																"Hello Sharad, \n\n This is a test mail from Springboot application. \n\n Regards, \n Team");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
