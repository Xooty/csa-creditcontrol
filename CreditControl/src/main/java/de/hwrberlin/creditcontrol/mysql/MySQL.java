package de.hwrberlin.creditcontrol.mysql;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

	public static String host = "ni5852421-1.web03.nitrado.hosting";
	public static String database = "ni5852421_1sql1";
	public static String user = "ni5852421_1sql1";
	public static String password = "&32asdZTG!";
	public static String port = "3306";

//	private File file;

	private User user_application;

	// Im Konstruktor wird �berpr�ft, ob die Datei mysql.txt vorhanden ist und
	// wird zur Not mit den Daten wieder gef�llt
	// Des Weiteren werden die n�tigen Tabellen mit Dummy Werten erstellt
//	public MySQL(String path) { // Path f�r Desktop: System.getProperty("user.home") + /Desktop/CreditControl/mysql.txt"
//
//		this.file = new File(path);
//		if (!this.file.exists()) {
//			
//			this.file.mkdir(); 
//			this.file = new File(path + "mysql.txt");
//			
//			PrintWriter writer = null;
//			try {
//				this.file.createNewFile();
//				
//				writer = new PrintWriter(new FileOutputStream(this.file, true));
//
//				String separator = System.getProperty("line.separator");
//				
//				writer.write("host: localhost" + separator);
//				writer.write("database: creditcontrol" + separator);
//				writer.write("user: root" + separator);
//				writer.write("password: 12345" + separator);
//				writer.write("port: 3306");
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				if (writer != null) {
//					writer.flush();
//					writer.close();
//		        }
//			}
//		}
//		
//		this.file = new File(path + "mysql.txt");
//		
//		BufferedReader reader;
//		
//		try {
//			reader = new BufferedReader(new FileReader(this.file));
//			
//			String line = reader.readLine();
//			
//			while (line != null) {
//				String[] args = line.split(": ");
//
//				switch (args[0]) {
//					case "host": this.host = args[1];
//					case "database": this.database = args[1];
//					case "user": this.user = args[1];
//					case "password": this.password = args[1];
//					case "port": this.port = args[1];
//				}
//				
//				line = reader.readLine();
//			}
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		this.initTables();
//	}

	public MySQL() {
	}

	public User setUser(User user) {
		this.user_application = user;
		return user;
	}

	public User getUser() {
		return this.user_application;
	}

	public static Connection openConnection() {
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

	// Methode zum Automatischen erstellen der Datenbank und der Tabellen mit Dummy
	// Werten
	public static void initTables() {

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
