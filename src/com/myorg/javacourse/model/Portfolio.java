package com.myorg.javacourse.model;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;

import com.myorg.javacourse.exception.BalanceException;
import com.myorg.javacourse.exception.PortfolioFullException;
import com.myorg.javacourse.exception.StockAlreadyExistsException;
import com.myorg.javacourse.exception.StockNotExistException;

/** build new portfolio instance */

/**
 * @author dorani
 *
 */
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
		this.balance = 0;
		
	}

	/** copy constructor 
	 * @throws PortfolioFullException 
	 * @throws StockAlreadyExistsException */
	public Portfolio(Portfolio portfolioToCopy) {
		this.setTitle(portfolioToCopy.getTitle());
		this.setStocks();
		this.setBalance(portfolioToCopy.getBalance());
		copyStocksArray(portfolioToCopy.getStocks(), this.getStocks());
	}
	


	public Portfolio(Stock[] stockArray) {
	
		
		this.title = new String();
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portfolioSize = stockArray.length;	
		this.copyStocksArray(stockArray, stocks);
		
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
	
	private void copyStocksArray(StockInterface[] oldStockInterfaces, StockInterface[] newStockInterfaces ){

		for(int i = 0; i<this.portfolioSize; i++){
			newStockInterfaces[i]= new Stock ((Stock)oldStockInterfaces[i]);

		}
	}
	
	/**update stock balance */
	public void updateBalance(float amount) throws BalanceException{
		
		if(amount == 0){
			System.out.println("you try to add to the balance 0 dollar");
		}
		else if(this.balance+amount >= 0){
		this.balance = this.balance + amount;
		}
		else{
			throw new BalanceException();
		}
		
		
	}
	
	
	/** add stock to the array*/
	public void addStock(Stock stock) throws StockAlreadyExistsException,PortfolioFullException {
		
		if (portfolioSize == MAX_PORTFOLIO_SIZE) {
			
			throw new PortfolioFullException(portfolioSize);
		}

		for (int i = 0; i < portfolioSize;i++) {

			if (findStock(stock.getSymbol()) != null)
			{
				
				throw new StockAlreadyExistsException(stock.getSymbol());				
							}		
		}
		
		this.stocks[portfolioSize] = stock;
		stock.setStockQuantity(0);
		this.portfolioSize++;


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
					this.portfolioSize--;
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
	public void buyStock(Stock stock, int quantity) throws BalanceException {

		if(quantity*stock.getAsk() > this.balance){
			
			throw new BalanceException();
			
		}
		if(quantity == -1){
					
			int buyAll = (int)this.balance/(int)this.stocks[this.findStockPlace(stock.getSymbol())].getAsk();
			
				this.updateBalance((-buyAll)*this.stocks[this.findStockPlace(stock.getSymbol())].getAsk());
		
			 (this.stocks[this.findStockPlace(stock.getSymbol())]).setStockQuantity(((Stock) this.stocks[this.findStockPlace(stock.getSymbol())]).getStockQuantity()+buyAll);
			
		}else {
			
				this.updateBalance(-quantity*this.stocks[this.findStockPlace(stock.getSymbol())].getAsk());	
			( this.stocks[this.findStockPlace(stock.getSymbol())]).setStockQuantity(((Stock) stocks[this.findStockPlace(stock.getSymbol())]).getStockQuantity()+quantity);		
			
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
		return this.balance;
	}
	
	public void setBalance(float balance){
		
		this.balance = balance;
	}
	
	/**get the value of the balance and the stocks value */
	public float getTotalValue(){
		
		float ret = getBalance()+ getStocksValue();
		return ret;
	}
	
	//find by symbol stock and get his place in the array

	public StockInterface findStock(String symbol) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < getPortfolioSize(); i++) {
			if (getStocks()[i].getSymbol().equals(symbol)){
				return this.getStocks()[i];
			}
		}
		return null;
		
	}
	
	//find the stock place in the array
	private int findStockPlace (String stockToFind){
		for(int i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
	}
}
