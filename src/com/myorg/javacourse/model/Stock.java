package com.myorg.javacourse.model;


import java.util.Date;

import org.algo.model.StockInterface;



public class Stock implements StockInterface {
	
	private String symbol;
	private float ask;
	private float bid;
	private Date date;
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	

	
	
	public enum ALGO_RECOMMENDATION{
		
		BUY(0), SELL(1), REMOVE(2), HOLD(3);
		private int value;
		
		private ALGO_RECOMMENDATION(int value){
			
			this.value = value;			
		}
		
	}
	



	/**stock constructor*/
	public Stock(String symbol, float ask, float bid, Date date,int stockQuantity) {
		this.symbol = symbol;
		this.ask = ask;
		this.bid = bid;
		this.date = date;
		this.stockQuantity = stockQuantity;
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
	
	public Stock() {
		this.symbol = new String();
		this.ask = 0;
		this.bid = 0;
		this.date = new Date();
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		this.stockQuantity = 0;
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
	
	
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
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
		
		return  "<b>Symbol: </b>" + getSymbol() +
				", <b>Ask: </b>" + getAsk() +
				", <b>Bid: </b>" + getBid() +
				", <b>Quantity: </b>" + getStockQuantity() +
				", <b>Date: </b>" + (getDate().getMonth()+1) + "/" + (getDate().getDate()) + "/" + ((getDate().getYear())+1900) + "<br>";
	}
	
}
