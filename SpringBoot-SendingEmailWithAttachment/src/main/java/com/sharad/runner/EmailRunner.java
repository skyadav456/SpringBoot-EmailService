package com.sharad.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sharad.service.PurchasedService;


@Component
public class EmailRunner implements CommandLineRunner {
	
	@Autowired
	private PurchasedService purchasedService;

	@Override
	public void run(String... args) throws Exception {
		String[] items = {"item1", "item2", "item3"};
		double[] prices = {100.0, 200.0, 300.0};
		String[] emails = {"sharadk4545@gmail.com","sharady4545@gmail.com","sharmapratiksha0786@gmail.com"};
		
		try {
		purchasedService.purchasedItem(items, prices, emails);
		} catch (Exception e) {
			System.out.println("Error occurred while sending email: " + e.getMessage());
		}

	}

}
