package com.myorg.javacourse.exception;

public class PortfolioFullException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PortfolioFullException(int size){
		super("you try to add stock while your max stocks limitation is"+size+",and it's full");
	}

}
