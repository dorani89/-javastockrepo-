package com.myorg.javacourse.exception;

public class BalanceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BalanceException(){
		super("Your balance can't become negative");
	}

}
