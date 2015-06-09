package com.myorg.javacourse.exception;

import org.algo.exception.PortfolioException;

public class StockNotExistException extends PortfolioException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockNotExistException(String symbol){
		super("The stock"+symbol+"dosen't exist in your portfolio");
	}

}
