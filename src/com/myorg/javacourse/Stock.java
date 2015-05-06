package com.myorg.javacourse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Stock {
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private int recommendation;
	private int stockQuantity;
	private final static int BUY = 0;
	private final static int SELL = 1;
	private final static int REMOVE = 2;
	private final static int HOLD = 3;



	/**stock constructor*/
	public Stock(String symbol, float ask, float bid, Date date) {
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
	}
	
	/**stock copy constructor*/
	public Stock(Stock stockToCopy)
	{	
		this.setSymbol(stockToCopy.getSymbol());
		this.setAsk(stockToCopy.getAsk());
		this.setBid(stockToCopy.getBid());
		this.setDate(stockToCopy.getDate());
		this.setRecommendation(stockToCopy.getRecommendation());
		this.setStockQuantity(stockToCopy.getStockQuantity());
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	
	
	public int getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	/**string of stock details*/
	public String getHtmlDescription(){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String date = df.format(getDate());
		
		String stockDetails = new String ("<b>symbol</b>: "+getSymbol()+ " <b>ask</b>: "+getAsk()+" <b>bid</b>: "+getBid()+" <b>date</b>: "+date);
		 
		return stockDetails;
		
	}



}
