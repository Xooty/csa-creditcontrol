package de.hwrberlin.creditcontrol.register;

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

public class UserRegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;  charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");

		PrintWriter printWriter = response.getWriter();

		UserRegisterBean bean = new UserRegisterBean();
		bean.setUsername(name);
		bean.setPassword(password);
		bean.setFirstName(first_name);
		bean.setLastName(last_name);
		bean.setEmail(email);
		request.getSession().setAttribute("customer_bean", bean);

		String s = validate(bean);

		if (s.equals("success")) {
			RequestDispatcher rd = request.getRequestDispatcher("loginform.jsp");
			rd.forward(request, response);
		} else if (s.equals("exists")) {
			printWriter.println("Dieser Benutzername existiert bereits!");
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			rd.include(request, response);
		} else if (s.equals("email")) {
			printWriter.println("Diese E-Mail-Adresse existiert bereits!");
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			rd.include(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public String validate(UserRegisterBean bean) {

		MySQL.initTables();
		
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		String valid = "";

		try {
			connection = MySQL.openConnection();
			st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ?");
			st.setString(1, bean.getUsername());

			rs = st.executeQuery();

			if (rs.first()) {
				MySQL.closeRessources(rs, st, connection);
				return valid = "exists";
			}
			rs.close();
			st.close();

			st = connection.prepareStatement("SELECT * FROM customers WHERE email = ?");
			st.setString(1, bean.getEmail());

			rs = st.executeQuery();

			if (rs.first()) {
				MySQL.closeRessources(rs, st, connection);
				return valid = "email";
			}

			rs.close();
			st.close();

			st = connection.prepareStatement("INSERT INTO customers (first_name, last_name, email) VALUES (?,?,?)");
			st.setString(1, bean.getFirstName());
			st.setString(2, bean.getLastName());
			st.setString(3, bean.getEmail());

			st.executeUpdate();
			st.close();

			int customer_id;

			st = connection
					.prepareStatement("SELECT * FROM customers WHERE first_name = ? AND last_name = ? AND email = ?");
			st.setString(1, bean.getFirstName());
			st.setString(2, bean.getLastName());
			st.setString(3, bean.getEmail());

			rs = st.executeQuery();

			rs.first();

			customer_id = rs.getInt("customer_id");

			st.close();

			st = connection.prepareStatement(
					"INSERT INTO users (customer_id, user_name, user_password, permission) VALUES (?,?,?,?)");
			st.setInt(1, customer_id);
			st.setString(2, bean.getUsername());
			st.setString(3, MySQL.toHexString(MySQL.getSHA(bean.getPassword())));
			st.setString(4, "CUSTOMER");

			st.executeUpdate();

			valid = "success";

			MySQL.closeRessources(rs, st, connection);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return valid;
	}
}