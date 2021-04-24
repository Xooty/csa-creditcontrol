package de.hwrberlin.creditcontrol.mysql;

public class CustomerController {
	
	private Customer model;
	private CustomerView view;
	public CustomerController(Customer model, CustomerView view) {
		
		this.model = model;
		this.view = view;
	}
	
	
}
