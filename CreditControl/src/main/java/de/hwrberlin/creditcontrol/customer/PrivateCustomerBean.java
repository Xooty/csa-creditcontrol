package de.hwrberlin.creditcontrol.customer;

import java.io.Serializable;

public class PrivateCustomerBean extends CustomerBean {

	private String cosigning;
	
	public PrivateCustomerBean() {
	
	}

	/**
	 * @return the cosigning
	 */
	public String getCosigning() {
		return cosigning;
	}

	/**
	 * @param cosigning the cosigning to set
	 */
	public void setCosigning(String cosigning) {
		this.cosigning = cosigning;
	}

	
	
	
}
