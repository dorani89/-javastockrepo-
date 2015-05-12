package com.myorg.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;

public class PortfolioManager {
	
	/**create portfolio with stocks*/
	
	public Portfolio getPortfolio() {
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 11, 15);
		
		Portfolio myPortfolio = new Portfolio();
		
		Stock st1 = new Stock("PIH", 10.0f, 8.5f, cal.getTime(),20);
		Stock st2 = new Stock("ALL", 30.0f, 25.5f, cal.getTime(),30);
		Stock st3 = new Stock("CAAS",20.0f, 15.5f, cal.getTime(),40);
		
		
		myPortfolio.setTitle("MyPortfolio");
		myPortfolio.updateBalance(10000);
		myPortfolio.buyStock(st1,20);
		myPortfolio.buyStock(st2,30);
		myPortfolio.buyStock(st3,40);
		myPortfolio.sellStock("ALL", -1);
		myPortfolio.removeStock("CAAS");
		

		return myPortfolio;	

	}
	

	

}
