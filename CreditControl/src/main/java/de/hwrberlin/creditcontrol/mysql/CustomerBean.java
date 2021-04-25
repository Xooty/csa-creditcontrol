package de.hwrberlin.creditcontrol.mysql;

import java.io.Serializable;
import java.util.Date;

import de.hwrberlin.creditcontrol.SalutationType;

public class CustomerBean implements Serializable {

	private SalutationType Type;
	private String firstname, surname;
	private Date brithdate;
	
	private String street;
	private int housenumber, postcode;
	
	private String identity_card_number;

	public CustomerBean() {
	}

	/**
	 * @return the type
	 */
	public SalutationType getType() {
		return Type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(SalutationType type) {
		Type = type;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the brithdate
	 */
	public Date getBrithdate() {
		return brithdate;
	}

	/**
	 * @param brithdate the brithdate to set
	 */
	public void setBrithdate(Date brithdate) {
		this.brithdate = brithdate;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the housenumber
	 */
	public int getHousenumber() {
		return housenumber;
	}

	/**
	 * @param housenumber the housenumber to set
	 */
	public void setHousenumber(int housenumber) {
		this.housenumber = housenumber;
	}

	/**
	 * @return the postcode
	 */
	public int getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the identity_card_number
	 */
	public String getIdentity_card_number() {
		return identity_card_number;
	}

	/**
	 * @param identity_card_number the identity_card_number to set
	 */
	public void setIdentity_card_number(String identity_card_number) {
		this.identity_card_number = identity_card_number;
	}
	
	
}
