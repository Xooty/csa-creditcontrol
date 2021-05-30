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

		UserBean bean = validate(name, password);
		
		if (bean != null) {
			request.getSession().setAttribute("login", bean);
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

	public UserBean validate(String username, String password) {
		
		MySQL.initTables();

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
			} else {
				return null;
			}

			st.close();
			rs.close();
			
			st = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?");
			st.setInt(1, customer_id);

			rs = st.executeQuery();
			
			if (rs.first()) {
				bean.setFirstName(rs.getString("first_name"));
				bean.setLastName(rs.getString("last_name"));
				bean.setEmail(rs.getString("email"));
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