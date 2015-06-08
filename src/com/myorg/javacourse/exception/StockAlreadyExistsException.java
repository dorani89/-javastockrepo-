package com.myorg.javacourse.exception;

public class StockAlreadyExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockAlreadyExistsException(String symbol){
		
		super("You try to add The stock"+symbol+" that she is already exist");
	}

}
