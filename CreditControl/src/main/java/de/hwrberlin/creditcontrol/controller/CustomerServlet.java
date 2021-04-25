package de.hwrberlin.creditcontrol.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hwrberlin.creditcontrol.beans.PrivateCustomerBean;


public class CustomerServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
	}
	
	String test = "müller";
	PrivateCustomerBean bean = new PrivateCustomerBean();
	
	bean.setCosigning(test);
}
