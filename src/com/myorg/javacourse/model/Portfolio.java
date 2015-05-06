package com.myorg.javacourse.model;


import com.myorg.javacourse.Stock;


/** build new portfolio instance*/

public class Portfolio {
	
	private String title;
	@SuppressWarnings("unused")
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portfolioSize = 0;
	
	/**portfolio constructor*/
	public Portfolio()
	{
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
	}
	
	/**copy constructor*/
	public Portfolio(Portfolio portfolioToCopy)
	{
		this.setTitle(portfolioToCopy.getTitle());
		this.setStocks();
		for (int i=0; i<portfolioToCopy.getPortfolioSize(); i++)
		{
			Stock tmp = new Stock(portfolioToCopy.stocks[i]);
			this.addStock(tmp);
		}
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
	
	public int getMaxPortfolioSize(){
		return MAX_PORTFOLIO_SIZE;
	}

	public void setStocks() {
		this.stocks = new Stock[getMaxPortfolioSize()];
	}
	
	public int getPortfolioSize(){
		
		return this.portfolioSize;
	}
	

	public void addStock(Stock stock){
		
		stocks[portfolioSize] = stock;
		portfolioSize++;		
	}
	
	/**create string with stocks*/
	public String getHtmlString(){
		
        String ret = "<h1>" + title + "</h1>";
        for (int i = 0; i < portfolioSize; i++)
        {
              ret = ret + stocks[i].getHtmlDescription()+ "<br/>";
        }
        return ret;
        		
	}
		

	/**remove stock from portfolio*/
	public void removeStock(String symbolToErase)
    {
          if (stocks[portfolioSize-1].getSymbol().equals(symbolToErase))
          {
                stocks[portfolioSize-1] = null;
                portfolioSize--;
          }
          else
          {
                for (int i=0; i < portfolioSize; i++)
                {
                      if (this.stocks[i].getSymbol().equals(symbolToErase))
                      {
                            this.stocks[i] = this.stocks[portfolioSize-1];
                            this.stocks[portfolioSize-1] = null;
                            portfolioSize--;
                      }
                }
          }
}
}
