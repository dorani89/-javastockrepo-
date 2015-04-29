package com.myorg.javacourse.model;

import com.myorg.javacourse.Stock;

public class Portfolio {
	
	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize = 0;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}

	public Stock[] getStocks() {
		return stocks;
	}
	

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
	
	public Portfolio (){
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}
	
	public void addStock(Stock stock){
		
		stocks[portfolioSize] = stock;
		portfolioSize++;		
	}
	
	public String getHtmlString(){
		
        String ret = "<h1>" + title + "</h1>";
        for (int i = 0; i < portfolioSize; i++)
        {
              ret = ret + stocks[i].getHtmlDescription()+ "<br/>";
        }
        return ret;
        		
	}
	
	


}
