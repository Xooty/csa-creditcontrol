package de.hwrberlin.creditcontrol.mysql;

public class CustomerController {
	
	private CustomerBean model;
	private CustomerView view;
	public CustomerController(CustomerBean model, CustomerView view) {
		
		this.model = model;
		this.view = view;
	}
	
	
}