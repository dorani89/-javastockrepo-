package com.myorg.javacourse.exception;

import org.algo.exception.PortfolioException;

public class StockAlreadyExistsException extends PortfolioException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String symbol){
		
		super("You try to add The stock  " +symbol+  " that she is already exist");
	}

}
