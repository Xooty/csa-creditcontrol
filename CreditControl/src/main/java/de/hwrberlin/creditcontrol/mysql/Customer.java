package de.hwrberlin.creditcontrol.mysql;

import java.util.Date;

public class Customer extends User {
	
	public enum SalutationType {
        MR("Herr"), MRS("Frau");

        private String type;

        private SalutationType(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }
	
	private SalutationType Type;
	private String firstname, surname;
	private Date brithdate;
	
	private String street;
	private int housenumber, postcode;
	
	private String identity_card_number;
	
	public Customer() {
	
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

	public Date getBrithdate() {
		return brithdate;
	}

	public void setBrithdate(Date brithdate) {
		this.brithdate = brithdate;
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
