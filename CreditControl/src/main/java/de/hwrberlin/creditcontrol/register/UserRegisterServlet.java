package de.hwrberlin.creditcontrol.register;

import java.io.IOException;
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

		UserRegisterBean bean = new UserRegisterBean();
		bean.setUsername(name);
		bean.setPassword(password);
		request.getSession().setAttribute("login", bean);
		request.getSession().setAttribute("username", name);

		if (validate(bean)) {
			RequestDispatcher rd = request.getRequestDispatcher("loginform.jsp");
			rd.forward(request, response);
		} else {
			
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public boolean validate(UserRegisterBean bean) {

		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		boolean valid = false;

		try {
			connection = MySQL.openConnection();
			st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ? AND user_password = ?");
			st.setString(1, bean.getUsername());
			st.setString(2, MySQL.toHexString(MySQL.getSHA(bean.getPassword())));

			rs = st.executeQuery();

			if (rs.first()) {
				valid = false;
			} else {
				rs.close();
				st.close();
				
				st = connection.prepareStatement("INSERT INTO users (user_name, user_password, first_name, last_name, email) VALUES (?,?,?,?,?)");
				st.setString(1, bean.getUsername());
				st.setString(2, MySQL.toHexString(MySQL.getSHA(bean.getPassword())));
				st.setString(3, bean.getFirstName());
				st.setString(4, bean.getLastName());
				st.setString(5, bean.getEmail());
				
				st.executeUpdate();
				
				valid = true;
			}
			
			MySQL.closeRessources(rs, st, connection);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return valid;
	}
}