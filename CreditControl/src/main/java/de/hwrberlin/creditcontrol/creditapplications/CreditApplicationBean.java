package de.hwrberlin.creditcontrol.creditapplications;

public class CreditApplicationBean {

	private String credit_usage, employer, employment_type, gross_income, credit_value, runtime, property_type, address;
	private int customer_id, credit_rate;
	private boolean verified;

	public String getCreditUsage() {
		return credit_usage;
	}

	public void setCreditUsage(String credit_usage) {
		this.credit_usage = credit_usage;
	}
	
	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}
	
	public String getEmploymentType() {
		return employment_type;
	}

	public void setEmploymentType(String employment_type) {
		this.employment_type = employment_type;
	}
	
	public String getGrossIncome() {
		return gross_income;
	}

	public void setGrossIncome(String gross_income) {
		this.gross_income = gross_income;
	}
	
	public String getCreditValue() {
		return credit_value;
	}

	public void setCreditValue(String credit_value) {
		this.credit_value = credit_value;
	}
	
	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	
	public String getPropertyType() {
		return this.property_type;
	}
	
	public void setPropertyType(String property_type) {
		this.property_type = property_type;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getCustomerID() {
		return this.customer_id;
	}
	
	public void setCustomerID(int customer_id) {
		this.customer_id = customer_id;
	}
	
	public int getCreditRate() {
		return this.credit_rate;
	}
	
	public void setCreditRate(int credit_rate) {
		this.credit_rate = credit_rate;
	}
	
	public boolean isVerified() {
		return this.verified;
	}
	
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
