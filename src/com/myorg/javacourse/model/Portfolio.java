package com.myorg.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import com.myorg.javacourse.exception.BalanceException;
import com.myorg.javacourse.exception.PortfolioFullException;
import com.myorg.javacourse.exception.StockAlreadyExistsException;
import com.myorg.javacourse.exception.StockNotExistException;

/** build new portfolio instance */

public class Portfolio implements PortfolioInterface {

	private String title;

	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize;
	private float balance;

	/** portfolio constructor */
	public Portfolio() {
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = 0;
		
	}

	/** copy constructor 
	 * @throws PortfolioFullException 
	 * @throws StockAlreadyExistsException */
	public Portfolio(Portfolio portfolioToCopy) {
		this.setTitle(portfolioToCopy.getTitle());
		this.setStocks();
		for (int i = 0; i < portfolioToCopy.getPortfolioSize(); i++) {
			Stock tmp = new Stock(portfolioToCopy.stocks[i]);
			try {
				this.addStock(tmp);
			} catch (StockAlreadyExistsException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (PortfolioFullException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	


	public Portfolio(Stock[] stockArray) {
	
		
		this.title = new String();
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		
		for (int i = 0; i < stockArray.length; i++) {
			try {
				addStock(stockArray[i]);
			} catch (StockAlreadyExistsException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (PortfolioFullException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
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
	public void updateBalance(float amount) throws BalanceException{
		
		if(amount == 0){
			System.out.println("you try to add to the balance 0 dollar");
		}
		else if(balance+amount >= 0){
		balance = balance + amount;
		}
		else{
			throw new BalanceException();
		}
	}
	
	
	/** add stock to the array*/
	public void addStock(Stock stock) throws StockAlreadyExistsException,PortfolioFullException {
		
		boolean exist = false;
		
	

		if (portfolioSize == MAX_PORTFOLIO_SIZE) {
			
			throw new PortfolioFullException(portfolioSize);
		}

		for (int i =0; i < portfolioSize;i++) {

			if (stocks[i].getSymbol() == stock.getSymbol())
			{
				exist = true;
				throw new StockAlreadyExistsException(stock.getSymbol());
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

	/** remove stock from portfolio 
	 * @throws BalanceException */
	public void removeStock(String symbolToErase) throws StockNotExistException, BalanceException  {
		
		boolean enter = true;

			for (int i = 0; i < getPortfolioSize(); i++) {
				if (getStocks()[i].getSymbol().equals(symbolToErase)) {
					sellStock(symbolToErase,getStocks()[i].getStockQuantity());
					getStocks()[i] = getStocks()[getPortfolioSize() - 1];
					getStocks()[getPortfolioSize() - 1] = null;
					setPortfolioSize(getPortfolioSize()-1);
					enter = false;
				}
				
			}
			if(enter == true){
			throw new StockNotExistException(symbolToErase);
			}
				
					
	}
	
	/**sell stock from portfolio 
	 * @throws BalanceException */
	public void sellStock(String stockToSell, int quantity) throws StockNotExistException, BalanceException {
		
		boolean enter = true;
		
		if(quantity == 0){
			enter = false;
		}
		
		else{


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
					enter = false;
				}
				
				
			}
			
				
			}
		}
		if(enter == true)
		throw new StockNotExistException(stockToSell);
		
		
		
		
		
	}
	
	
	/**buy stock 
	 * @throws PortfolioFullException 
	 * @throws StockAlreadyExistsException */
	public void buyStock(Stock stock, int quantity) throws BalanceException, StockAlreadyExistsException, PortfolioFullException {

		if ((getBalance() < stock.getAsk()) || (getBalance()/stock.getAsk() < quantity)){
			throw new BalanceException();
		}
		else{
			if (quantity == -1) quantity = (int)(getBalance()/stock.getAsk());
			for (int i=0; i<=getPortfolioSize(); i++){
				if (i == getPortfolioSize()) addStock(stock);
				if (getStocks()[i].getSymbol().equals(stock.getSymbol())){
					updateBalance((quantity*stock.getAsk())*(-1));
					getStocks()[i].setStockQuantity(getStocks()[i].getStockQuantity() + quantity);
					
				}
			}
		}
		
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
