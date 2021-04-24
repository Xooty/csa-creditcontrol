package de.hwrberlin.creditcontrol.mysql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import de.hwrberlin.creditcontrol.Main;

public class MySQL {

	private String host;
	private String database;
	private String user;
	private String password;
	private String port;

	private File file;
	
	private User user_application;

	// Im Konstruktor wird überprüft, ob die Datei mysql.txt vorhanden ist und wird zur Not mit den Daten wieder gefüllt
	// Des Weiteren werden die nötigen Tabellen mit Dummy Werten erstellt
	public MySQL(String path) { // Path für Desktop: System.getProperty("user.home") + /Desktop/CreditControl/mysql.txt"

		this.file = new File(path);
		if (!this.file.exists()) {
			
			this.file.mkdir(); 
			this.file = new File(path + "mysql.txt");
			
			PrintWriter writer = null;
			try {
				this.file.createNewFile();
				
				writer = new PrintWriter(new FileOutputStream(this.file, true));

				String separator = System.getProperty("line.separator");
				
				writer.write("host: localhost" + separator);
				writer.write("database: creditcontrol" + separator);
				writer.write("user: root" + separator);
				writer.write("password: 12345" + separator);
				writer.write("port: 3306");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (writer != null) {
					writer.flush();
					writer.close();
		        }
			}
		}
		
		this.file = new File(path + "mysql.txt");
		
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(this.file));
			
			String line = reader.readLine();
			
			while (line != null) {
				String[] args = line.split(": ");

				switch (args[0]) {
					case "host": this.host = args[1];
					case "database": this.database = args[1];
					case "user": this.user = args[1];
					case "password": this.password = args[1];
					case "port": this.port = args[1];
				}
				
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.initTables();
	}
	
	public User setUser(User user) {
		this.user_application = user;
		return user;
	}
	
	public User getUser() {
		return this.user_application;
	}

	public Connection openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.user, this.password);
			return conn;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void queryUpdate(String query) {
		Connection conn = this.openConnection();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Failed to send query update '" + query + "'");
		} finally {
			this.closeRessources(null, st, conn);
		}
	}

	public void closeRessources(ResultSet rs, PreparedStatement st, Connection connection) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) { }
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) { }
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) { }
		}
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Methode zum Automatischen erstellen der Datenbank und der Tabellen mit Dummy Werten
	public void initTables() {

		Connection connection = null;
		PreparedStatement st = null;
//		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/", this.user, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			st = connection.prepareStatement("CREATE DATABASE IF NOT EXISTS " + this.database);
			st.executeUpdate();
			st.close();
			this.closeConnection(connection);
			
			connection = this.openConnection();
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS employees (user_id INTEGER AUTO_INCREMENT PRIMARY KEY, user_name VARCHAR(50), user_password VARCHAR(50), permissions VARCHAR(50))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS credits (credit_id INTEGER AUTO_INCREMENT PRIMARY KEY, employee_id INTEGER, customer_id INTEGER, credit_value DOUBLE, open_credit_value DOUBLE, credit_rate_value DOUBLE, interest_rate DOUBLE, credit_start DATE, credit_end DATE)");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS credits_personal (credit_id INTEGER, usage_type VARCHAR(50))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS credits_mortgage (credit_id INTEGER, address VARCHAR(200), property_type VARCHAR(200))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS inquiries (inquiry_id INTEGER AUTO_INCREMENT PRIMARY KEY, credit_id INTEGER, employee_id INTEGER, customer_id INTEGER, verified BOOLEAN)");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS customers (customer_id INTEGER AUTO_INCREMENT PRIMARY KEY, salutation VARCHAR(10), first_name VARCHAR(50), surname VARCHAR(50), birth_date VARCHAR(50), address VARCHAR(500), email VARCHAR(200), phone VARCHAR(50))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS customers_personal (customer_id INTEGER, guarantee VARCHAR(200))");
			st.executeUpdate();
			st.close();
			
			st = connection.prepareStatement("CREATE TABLE IF NOT EXISTS customers_business (customer_id INTEGER, verified BOOLEAN, company_name VARCHAR(200))");
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeRessources(null, st, connection);
		}
	}
	
	// Methode zum Einloggen
	public User login(String user_name, String user_password) {
		MySQL mysql = Main.getMySQL();
		Connection connection = mysql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ? AND user_password = ?");
			st.setString(1, user_name);
			st.setString(2, user_password);
			
			rs = st.executeQuery();
			
			if (rs.first()) {
				return mysql.setUser(new User(rs.getInt("user_id"), connection));
			} else {
				return null;
			}
		} catch (NullPointerException e) {
			System.err.println("Der User konnte nicht geladen werden.");
			mysql.closeRessources(rs, st, connection);
			return null;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			mysql.closeRessources(rs, st, connection);
		}
		return null;
	}
	
	// Methode zum Erstellen eines Benutzers
	public boolean createUser(String user_name, String password, String permission) {
		MySQL sql = Main.getMySQL();
    	Connection connection = sql.openConnection();
    	PreparedStatement st = null;
    	ResultSet rs = null;
    	
    	try {
    		st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ?");
    		st.setString(1, user_name);
    		rs = st.executeQuery();
    		
    		if (rs.first()) {
    			return false;
    		} else {
    			st.close();
    			
    			st = connection.prepareStatement("INSERT INTO users (user_name, user_password, permissions) VALUES (?, ?, ?)");
    			st.setString(1, user_name);
    			st.setString(2, password);
    			st.setString(3, permission);
    			
    			st.executeUpdate();
    			
    			return true;
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		sql.closeRessources(rs, st, connection);
    	}
    	return false;
	}
	
	// Methode zum Suchen eines Benutzers durch den Benutzernamen
	public User searchUser(String user_name) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ?");
			st.setString(1, user_name);
			rs = st.executeQuery();
			
			if (rs.first()) {
				return new User(rs.getInt("user_id"), connection);
			} else {
				JOptionPane.showMessageDialog(null, "Dieser Benutzername existiert nicht.");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(rs, st, connection);
		}
		return null;
	}
	
	// Methode zum Löschen eines Benutzers
	public void deleteUser(User user) {
		MySQL sql = Main.getMySQL();
		Connection connection = sql.openConnection();
		PreparedStatement st = null;
		
		try {
			st = connection.prepareStatement("DELETE FROM users WHERE user_id = ? AND user_name = ?");
			st.setInt(1, user.getUserID());
			st.setString(2, user.getUserName());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			sql.closeRessources(null, st, connection);
		}
	}
}
