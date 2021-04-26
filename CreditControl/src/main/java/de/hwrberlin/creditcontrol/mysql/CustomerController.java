package de.hwrberlin.creditcontrol.mysql;

import de.hwrberlin.creditcontrol.customer.CustomerBean;

public class CustomerController {
	
	private CustomerBean model;
	private CustomerView view;
	public CustomerController(CustomerBean model, CustomerView view) {
		
		this.model = model;
		this.view = view;
	}
	
	
}
