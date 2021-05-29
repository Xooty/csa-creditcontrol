package de.hwrberlin.creditcontrol.inquiry;

public class CreditApplicationBean {

	private String credit_usage, employer, employment_type, gross_income, credit_value, runtime, mortage_type, address;

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
	
	public String getMortageType() {
		return this.mortage_type;
	}
	
	public void setMortageType(String mortage_type) {
		this.mortage_type = mortage_type;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
