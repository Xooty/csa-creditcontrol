package de.hwrberlin.creditcontrol.mysql;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MySQL {

	public MySQL() { }

	public static Connection openConnection() {
		
		InputStream inputStream = MySQL.class.getResourceAsStream("/de/hwrberlin/creditcontrol/config.properties");
		Properties props = new Properties();
		try {
			props.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String host = props.getProperty("host", "localhost");
		String database = props.getProperty("database", "creditcontrol");
		String user = props.getProperty("user", "root");
		String password = props.getProperty("password", "12345");
		String port = props.getProperty("port", "3306");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user,
					password);
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void queryUpdate(String query) {
		Connection conn = openConnection();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Failed to send query update '" + query + "'");
		} finally {
			closeRessources(null, st, conn);
		}
	}

	public static void closeRessources(ResultSet rs, PreparedStatement st, Connection connection) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Methode zum Automatischen erstellen der Datenbank und der Tabellen
	// Es werden die Tabellen wie im ER Modell beschrieben, erstellt
	public static void initTables() {
		
		InputStream inputStream = MySQL.class.getResourceAsStream("/de/hwrberlin/creditcontrol/config.properties");
		Properties props = new Properties();
		try {
			props.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String host = props.getProperty("host", "localhost");
		String database = props.getProperty("database", "creditcontrol");
		String user = props.getProperty("user", "root");
		String password = props.getProperty("password", "12345");
		String port = props.getProperty("port", "3306");
		
		Connection connection = null;
		PreparedStatement st = null;
//		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/", user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			st = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + database);
			st.executeUpdate();
			st.close();
			closeConnection(connection);

			connection = openConnection();
			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS employees (user_id INTEGER AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(50), user_password VARCHAR(50), permissions VARCHAR(50))");
			st.executeUpdate();
			st.close();

			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS credits (credit_id INTEGER AUTO_INCREMENT PRIMARY KEY, employee_id INTEGER, customer_id INTEGER, credit_value DOUBLE, open_credit_value DOUBLE, credit_rate_value DOUBLE, interest_rate DOUBLE, credit_start DATE, credit_end DATE)");
			st.executeUpdate();
			st.close();

			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS credits_personal (credit_id INTEGER, usage_type VARCHAR(50))");
			st.executeUpdate();
			st.close();

			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS credits_mortage (credit_id INTEGER, address VARCHAR(200), property_type VARCHAR(200))");
			st.executeUpdate();
			st.close();

			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS credit_applications_private (credit_application_id INTEGER AUTO_INCREMENT PRIMARY KEY, credit_id INTEGER, employee_id INTEGER, customer_id INTEGER, verified BOOLEAN, credit_usage VARCHAR(50), credit_value INTEGER, runtime INTEGER, employer VARCHAR(50), employment_type VARCHAR(50), gross_income VARCHAR(50))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS credit_applications_mortage (credit_application_id INTEGER AUTO_INCREMENT PRIMARY KEY, credit_id INTEGER, employee_id INTEGER, customer_id INTEGER, verified BOOLEAN, property_type VARCHAR(50), credit_value INTEGER, runtime INTEGER, employer VARCHAR(50), employment_type VARCHAR(50), gross_income VARCHAR(50), address VARCHAR(100))");
			st.executeUpdate();
			st.close();

			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS customers (customer_id INTEGER AUTO_INCREMENT PRIMARY KEY, salutation VARCHAR(10), first_name VARCHAR(50), last_name VARCHAR(50), birth_date VARCHAR(50), address VARCHAR(500), email VARCHAR(200), phone VARCHAR(50))");
			st.executeUpdate();
			st.close();

			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS customers_personal (customer_id INTEGER, guarantee VARCHAR(200))");
			st.executeUpdate();
			st.close();

			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS customers_business (customer_id INTEGER, verified BOOLEAN, company_name VARCHAR(200))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS users (user_id INTEGER AUTO_INCREMENT PRIMARY KEY, customer_id INTEGER, user_name VARCHAR(50), user_password VARCHAR(250), permission VARCHAR(50))");
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeRessources(null, st, connection);
		}
	}

	// Die folgenden beiden Methoden zum Verschlüsseln der Passwörter auf Basis von SHA256
	public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
		// Static getInstance method is called with hashing SHA
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		// digest() method called
		// to calculate message digest of an input
		// and return array of byte
		return md.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	public static String toHexString(byte[] hash) {
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}
}
