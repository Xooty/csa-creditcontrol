package de.hwrberlin.creditcontrol.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hwrberlin.creditcontrol.customer.PrivateCustomerBean;


public class CustomerServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
	
        String cosignign = "";
        SalutationType type = SalutationType.MR;
        String firstname = "";
        String surname = "";
        Date date = new Date();
        
        
    	PrivateCustomerBean bean = new PrivateCustomerBean();
    	
    	bean.setCosigning(cosignign);
    	bean.setType(type);
    	bean.setFirstname(firstname);
    	bean.setSurname(surname);
    	bean.setBirthdate(date);
	}
	
}
