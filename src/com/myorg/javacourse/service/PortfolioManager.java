package com.myorg.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.myorg.javacourse.Stock;
import com.myorg.javacourse.model.Portfolio;

public class PortfolioManager {
	
	public Portfolio getPortfolio() {
		
		Portfolio portfolio = new Portfolio();
		
		Calendar cal = Calendar.getInstance();
		cal.set(2014,10,15);
		
		
		Date date1 = cal.getTime();
		Stock stock1 = new Stock("PIH", (float)13.1,(float)12.4, date1);
		
		Date date2 = cal.getTime();
		Stock stock2 = new Stock("ALL", (float) 5.78, (float) 5.5, date2);
		
		Date date3 = cal.getTime();
		Stock stock3 = new Stock("CAAS",(float) 32.2,(float) 31.5, date3);
		
		portfolio.setTitle("Portfolio 1");
		portfolio.addStock(stock1);	
		portfolio.addStock(stock2);	
		portfolio.addStock(stock3);
	
		return portfolio;
		
	}

}
