package com.myorg.javacourse.exception;

import org.algo.exception.PortfolioException;

public class PortfolioFullException extends PortfolioException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PortfolioFullException(int size){
		super("you try to add stock while your max stocks limitation is"+size+",and it's full");
	}

}
