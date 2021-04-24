package de.hwrberlin.creditcontrol;

import de.hwrberlin.creditcontrol.mysql.MySQL;

public class Main {
	
	public static MySQL mysql;
	
	public static void main(String[] args) {
		mysql = new MySQL(System.getProperty("user.home") + "/Desktop/CreditControl/");
		
	}
	
	public static MySQL getMySQL() {
		return mysql;
	}
}
