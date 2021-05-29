package de.hwrberlin.creditcontrol.customer;

import java.io.Serializable;
import java.util.Date;

import de.hwrberlin.creditcontrol.controller.SalutationType;

public class CustomerBean implements Serializable {

	protected SalutationType Type;
	protected String firstname, surname;
	protected Date birthdate;
	
	protected String street;
	protected int housenumber, postcode;
	
	protected String identity_card_number;

	public CustomerBean() {
	}

	public SalutationType getType() {
		return Type;
	}

	public void setType(SalutationType type) {
		Type = type;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(int housenumber) {
		this.housenumber = housenumber;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getIdentity_card_number() {
		return identity_card_number;
	}

	public void setIdentity_card_number(String identity_card_number) {
		this.identity_card_number = identity_card_number;
	}
	
	
}
