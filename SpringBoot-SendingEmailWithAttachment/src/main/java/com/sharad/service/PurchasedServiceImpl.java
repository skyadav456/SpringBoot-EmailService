package com.sharad.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class PurchasedServiceImpl implements PurchasedService {
	
	@Autowired
	private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String fromEmail;

	@Override
	public String purchasedItem(String[] items, double[] prices, String[] emails) throws Exception {
		if (items == null || prices == null || emails == null) {
			throw new IllegalArgumentException("Items, prices, and emails must not be null");
		}else {
			double billAmount = 0.0;
			for(double price:prices){
				billAmount += price;
			}
			String mssg1= Arrays.toString(items)+" -----"+ Arrays.toString(prices)+ "are purchased. Total bill amount is: "+billAmount;
			// invoking sendMessage method to send email
			String status = sendMessage(mssg1, emails);
			return mssg1 +"---"+ status;
			}
		}
	
	public String sendMessage(String mssg, String[] toEmails) throws Exception {
		if (toEmails == null || toEmails.length == 0) {
			throw new IllegalArgumentException("Recipient emails must not be null or empty");
		}
		
		MimeMessage message= sender.createMimeMessage();
		MimeMessageHelper helper= new MimeMessageHelper(message, true);
		//set header
		helper.setFrom(fromEmail);
		helper.setTo(toEmails);
		helper.setSubject("Purchased Items");
		
		//set body
		helper.setText(mssg);
		helper.addAttachment("items.png", new ClassPathResource("Nat.png"));
		sender.send(message);
		return "Mail Sended Successfully to "+Arrays.toString(toEmails);
		
	}

}
