package com.myorg.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

/** build new portfolio instance */

public class Portfolio implements PortfolioInterface {

	private String title;

	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize = 0;
	private float balance;

	/** portfolio constructor */
	public Portfolio() {
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}

	/** copy constructor */
	public Portfolio(Portfolio portfolioToCopy) {
		this.setTitle(portfolioToCopy.getTitle());
		this.setStocks();
		for (int i = 0; i < portfolioToCopy.getPortfolioSize(); i++) {
			Stock tmp = new Stock(portfolioToCopy.stocks[i]);
			this.addStock(tmp);
		}
	}
	


	public Portfolio(Stock[] stockArray) {
		// TODO Auto-generated constructor stub
		
		this.title = new String();
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		
		for (int i = 0; i < stockArray.length; i++) {
			addStock(stockArray[i]);
		}
		
		this.balance = 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Stock[] getStocks() {
		return stocks;
	}

	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}

	public void setStocks() {
		this.stocks = new Stock[getMaxPortfolioSize()];
	}

	public int getPortfolioSize() {

		return this.portfolioSize;
	}
	
	public void setPortfolioSize(int portfolioSize) {

		this.portfolioSize = portfolioSize;
	}
	
	
	/**update stock balance */
	public void updateBalance(float amount){
		
		if(amount == 0){
			System.out.println("you try to add to the balance 0 dollar");
		}
		else if(balance+amount >= 0){
		balance = balance + amount;
		}
		else{
			System.out.println("your balance can't be negative");
		}
	}
	
	
	/** add stock to the array*/
	public void addStock(Stock stock) {
		
		boolean exist = false;

		if (portfolioSize == MAX_PORTFOLIO_SIZE) {
			System.out.println("Can’t add new stock, portfolio can have only"
					+ portfolioSize + "stocks");
		}

		for (int i =0; i < portfolioSize;) {

			if (stocks[i].getSymbol() == stock.getSymbol())
			{
				exist = true;
				i = portfolioSize;
			}
			else{
				i++;
			}
		}

		if(exist==false){
			this.stocks[portfolioSize] = stock;
			stock.setStockQuantity(0);
			portfolioSize++;
		}

	}

	/** create string with stocks */
	public String getHtmlString() {

		String ret = "<h1>" + title + "</h1>" + "<br/>"
				+ "<b>Total Portfolio Value: </b>: " + getTotalValue()+"   <b>$</b>    "
				+    "  <b>Total Stocks value: </b>: " + getStocksValue()+"   <b>$</b>    "
				+    "  <b> Balance: </b>: " + getBalance()+"<b>$</b>"+"<br/>"+"<br/>";
		for (int i = 0; i < getPortfolioSize(); i++) {
			ret += "" + getStocks()[i].getHtmlDescription() + "<br/>";
		}
		return ret;

	}

	/** remove stock from portfolio */
	public boolean removeStock(String symbolToErase) {
		
		

			for (int i = 0; i < getPortfolioSize(); i++) {
				if (getStocks()[i].getSymbol().equals(symbolToErase)) {
					if(sellStock(symbolToErase,getStocks()[i].getStockQuantity())== true){
					getStocks()[i] = getStocks()[getPortfolioSize() - 1];
					getStocks()[getPortfolioSize() - 1] = null;
					setPortfolioSize(getPortfolioSize()-1);
					return true;
					}
					else{
						i++;
					}
				}
			}
				return false;
					
	}
	
	/**sell stock from portfolio */
	public boolean sellStock(String stockToSell, int quantity) {


		for (int i = 0; i < getPortfolioSize(); i++) {
			if (getStocks()[i].getSymbol().equals(stockToSell)) {
				if (quantity == -1) quantity = getStocks()[i].getStockQuantity();
				if (getStocks()[i].getStockQuantity() < quantity) {
					System.out.println("Not enough stocks to sell");
					break;
				}else{
					float amount = getStocks()[i].getStockQuantity() * getStocks()[i].getBid();
					updateBalance(amount);
					getStocks()[i].setStockQuantity(getStocks()[i].getStockQuantity() - quantity);
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**buy stock */
	public boolean buyStock(Stock stock, int quantity) {

		if ((getBalance() < stock.getAsk()) || (getBalance()/stock.getAsk() < quantity)){
			System.out.println("Not enough balance to complete purchase.");
		}else{
			if (quantity == -1) quantity = (int)(getBalance()/stock.getAsk());
			for (int i=0; i<=getPortfolioSize(); i++){
				if (i == getPortfolioSize()) addStock(stock);
				if (getStocks()[i].getSymbol().equals(stock.getSymbol())){
					updateBalance((quantity*stock.getAsk())*(-1));
					getStocks()[i].setStockQuantity(getStocks()[i].getStockQuantity() + quantity);
					return true;
				}
			}
		}
		return false;
	}
	
	/**get the value of all stocks */
	public float getStocksValue(){
		
		float ret = 0;
		for (int i = 0; i < portfolioSize; i++){
			
			ret = ret + stocks[i].getBid()*stocks[i].getStockQuantity();
		}
		return ret;
		
	}
	/**get the current balance */
	public float getBalance(){
		return balance;
	}
	
	/**get the value of the balance and the stocks value */
	public float getTotalValue(){
		
		float ret = getBalance()+ getStocksValue();
		return ret;
	}

	public StockInterface findStock(String symbol) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < getPortfolioSize(); i++) {
			if (getStocks()[i].getSymbol().equals(symbol)){
				return this.getStocks()[i];
			}
		}
		return null;
	}
}
