package com.myorg.javacourse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MyNewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		
		int radius = 50;
		int exponent = 2;	
		double area =  Math.PI*Math.pow(radius, exponent);
		
		
		int angelB = 30;
		int hypotenuse = 50;
		double opposite = Math.sin( Math.toRadians(angelB) )*(hypotenuse);
		
		
		int base = 20;
		int exp = 13;
		double result = Math.pow(base, exp);
		
		
		
				
		String line1 = new String ("calculation 1: Area of circle with radius:  "+radius+" is: "+area+" square- cm.");
		
		String line2 = new String ("calculation 2: Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is: "+opposite+"cm.");
			
		String line3 = new String ("calculation 3: power of "+base+" with exp of "+exp+" is "+result+"");
		
		String resultStr = line1 + "<br>"+ line2 + "<br>" +line3;
					
		resp.getWriter().println(resultStr);
		
		
		
	}

}
