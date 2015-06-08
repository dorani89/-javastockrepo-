package com.myorg.javacourse.exception;

public class StockNotExistException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StockNotExistException(String symbol){
		super("The stock"+symbol+"dosen't exist in your portfolio");
	}

}
