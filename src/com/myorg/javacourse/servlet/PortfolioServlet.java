package com.myorg.javacourse.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myorg.javacourse.model.Portfolio;
import com.myorg.javacourse.model.Stock;
import com.myorg.javacourse.service.PortfolioManager;

@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PortfolioManager portfolioManager = new PortfolioManager();
		
		Portfolio myPortfolio = portfolioManager.getPortfolio();
	

		resp.getWriter().println(myPortfolio.getHtmlString());
		


	}

}
