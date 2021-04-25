package de.hwrberlin.creditcontrol;

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
