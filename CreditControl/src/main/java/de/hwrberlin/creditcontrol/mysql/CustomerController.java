package de.hwrberlin.creditcontrol.mysql;

import de.hwrberlin.creditcontrol.beans.CustomerBean;

public class CustomerController {
	
	private CustomerBean model;
	private CustomerView view;
	public CustomerController(CustomerBean model, CustomerView view) {
		
		this.model = model;
		this.view = view;
	}
	
	
}
