package de.hwrberlin.creditcontrol.beans;

import java.io.Serializable;

public class PrivateCustomerBean extends CustomerBean {

	private String cosignign;
	
	public PrivateCustomerBean() {
	
	}

	/**
	 * @return the cosignign
	 */
	public String getCosignign() {
		return cosignign;
	}

	/**
	 * @param cosignign the cosignign to set
	 */
	public void setCosignign(String cosignign) {
		this.cosignign = cosignign;
	}
	
	
}
