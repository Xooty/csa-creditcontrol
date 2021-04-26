package de.hwrberlin.creditcontrol.login;

import java.io.IOException;
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

		LoginBean bean = new LoginBean();
		bean.setUsername(name);
		bean.setPassword(password);
		request.getSession().setAttribute("login", bean);
		request.getSession().setAttribute("username", name);

		if (validate(bean)) {
			RequestDispatcher rd = request.getRequestDispatcher("website_after_login.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("loginform.jsp");
			rd.forward(request, response);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public boolean validate(LoginBean bean) {

		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		boolean valid = false;

		try {
			connection = MySQL.openConnection();
			st = connection.prepareStatement("SELECT * FROM users WHERE user_name = ? AND user_password = ?");
			st.setString(1, bean.getUsername());
			st.setString(2, bean.getPassword());

			rs = st.executeQuery();

			if (rs.first()) {
				valid = true;
			}

			MySQL.closeRessources(rs, st, connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return valid;
	}
}