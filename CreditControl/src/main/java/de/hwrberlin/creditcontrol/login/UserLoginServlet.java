package de.hwrberlin.creditcontrol.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hwrberlin.creditcontrol.mysql.MySQL;

public class UserLoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;  charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter printWriter = response.getWriter();

		// Falls keine Datenbankverbindung hergestellt werden kann, wird auf die loginform.jsp zurückgeleitet und eine Fehlermeldung ausgegeben
		Connection conn = MySQL.openConnection(); 
		if (conn == null) {
			printWriter.println("Es konnte keine Verbindung mit der Datenbank hergestellt werden!");
			RequestDispatcher rd = request.getRequestDispatcher("loginform.jsp");
			rd.include(request, response);
			return;
		}
		MySQL.closeConnection(conn);
		
		UserBean bean = validate(name, password, request);
		
		if (bean != null) {
			request.getSession().setAttribute("login", bean);
			request.getSession().setAttribute("customer_id", bean.getCustomerID());
			request.getSession().setAttribute("first_name", bean.getFirstName());
			request.getSession().setAttribute("last_name", bean.getLastName());
			request.getSession().setAttribute("email", bean.getEmail());
			RequestDispatcher rd = request.getRequestDispatcher("website_after_login.jsp");
			rd.forward(request, response);
		} else {
			printWriter.println("Bitte überprüfen Sie den Benutzernamen oder das Passwort!");
			RequestDispatcher rd = request.getRequestDispatcher("loginform.jsp");
			rd.include(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public UserBean validate(String username, String password, HttpServletRequest request) {
		
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		UserBean bean = new UserBean();
		bean.setUsername(username);
		bean.setPassword(password);
		
		int customer_id = 0;

		try {
			connection = MySQL.openConnection();
			st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ? AND user_password = ?");
			st.setString(1, bean.getUsername());
			st.setString(2, MySQL.toHexString(MySQL.getSHA(bean.getPassword())));
			rs = st.executeQuery();

			if (rs.first()) {
				customer_id = rs.getInt("customer_id");
				bean.setCustomerID(customer_id);
			} else {
				return null;
			}

			st.close();
			rs.close();
			
			st = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?");
			st.setInt(1, customer_id);

			rs = st.executeQuery();
			
			if (rs.first()) {
				String first_name = rs.getString("first_name");
				bean.setFirstName(first_name);
				bean.setLastName(rs.getString("last_name"));
				bean.setEmail(rs.getString("email"));
			}
			
			rs.close();
			st.close();
			
			st = connection.prepareStatement("SELECT * FROM credit_applications_private WHERE customer_id = ?");
			st.setInt(1, customer_id);
			
			rs = st.executeQuery();
			
			if (rs.first()) {
				request.getSession().setAttribute("credit_usage_private", rs.getString("credit_usage"));
				request.getSession().setAttribute("credit_value_private", rs.getString("credit_value") + " €");
				request.getSession().setAttribute("credit_runtime_private", rs.getString("runtime") + " Monate");
				request.getSession().setAttribute("credit_verified_private", rs.getBoolean("verified"));
				request.getSession().setAttribute("credit_rate_private", (Integer.valueOf(rs.getString("credit_value")) / Integer.valueOf(rs.getString("runtime"))) + " € / Monat");
			} else {
				request.getSession().setAttribute("credit_usage_private", "Kein Kredit vorhanden");
				request.getSession().setAttribute("credit_value_private", "Kein Kredit vorhanden");
				request.getSession().setAttribute("credit_runtime_private", "Kein Kredit vorhanden");
				request.getSession().setAttribute("credit_verified_private", "Kein Kredit vorhanden");
				request.getSession().setAttribute("credit_rate_private", "Kein Kredit vorhanden");
			}
			
			rs.close();
			st.close();
			
			st = connection.prepareStatement("SELECT * FROM credit_applications_mortage WHERE customer_id = ?");
			st.setInt(1, customer_id);
			
			rs = st.executeQuery();
			
			if (rs.first()) {
				request.getSession().setAttribute("credit_property_type_mortage", rs.getString("property_type"));
				request.getSession().setAttribute("credit_value_mortage", rs.getString("credit_value") + " €");
				request.getSession().setAttribute("credit_runtime_mortage", rs.getString("runtime") + " Monate");
				request.getSession().setAttribute("credit_verified_mortage", rs.getBoolean("verified"));
				request.getSession().setAttribute("credit_rate_mortage", (Integer.valueOf(rs.getString("credit_value")) / Integer.valueOf(rs.getString("runtime"))) + " € / Monat");
				request.getSession().setAttribute("credit_address_mortage", rs.getString("address"));
			} else {
				request.getSession().setAttribute("credit_property_type_mortage", "Kein Kredit vorhanden");
				request.getSession().setAttribute("credit_value_mortage", "Kein Kredit vorhanden");
				request.getSession().setAttribute("credit_runtime_mortage", "Kein Kredit vorhanden");
				request.getSession().setAttribute("credit_verified_mortage", "Kein Kredit vorhanden");
				request.getSession().setAttribute("credit_rate_mortage", "Kein Kredit vorhanden");
				request.getSession().setAttribute("credit_address_mortage", "Kein Kredit vorhanden");
			}
			
			MySQL.closeRessources(rs, st, connection);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return bean;
	}
}