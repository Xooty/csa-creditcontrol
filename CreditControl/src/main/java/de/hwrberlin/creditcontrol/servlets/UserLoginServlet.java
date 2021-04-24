package de.hwrberlin.creditcontrol.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import de.hwrberlin.creditcontrol.Main;
import de.hwrberlin.creditcontrol.mysql.User;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public UserLoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = Main.getMySQL().getUser();
		String destPage = "login.jsp";
		
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			destPage = "home.jsp";
		} else {
			String message = "Invalid email/password";
			request.setAttribute("message", message);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}