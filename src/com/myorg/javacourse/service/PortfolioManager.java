package com.myorg.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.myorg.javacourse.Stock;
import com.myorg.javacourse.model.Portfolio;

public class PortfolioManager {
	
	/**create portfolio with stocks*/
	
	public Portfolio getPortfolio() {
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 11, 15);
		
		Portfolio portfolio = new Portfolio();
		
		Stock st1 = new Stock("PIH", 13.1f, 12.4f, cal.getTime());
		Stock st2 = new Stock("ALL", 5.78f, 5.5f, cal.getTime());
		Stock st3 = new Stock("CAAS", 32.2f, 31.5f, cal.getTime());
		
		portfolio.setTitle("Portfolio 1");
		portfolio.addStock(st1);
		portfolio.addStock(st2);
		portfolio.addStock(st3);
		

		return portfolio;	

	}
	

	

}
