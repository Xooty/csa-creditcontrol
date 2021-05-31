package de.hwrberlin.creditcontrol.creditapplications;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hwrberlin.creditcontrol.mysql.MySQL;

public class CreditApplicationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;  charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String credit_usage = request.getParameter("form_verwendungszweck");
		String employer = request.getParameter("form_arbeitgeber");
		String employment_type = request.getParameter("form_verhaeltnis");
		String gross_income = request.getParameter("form_bruttoeinkommen");
		String credit_value = request.getParameter("form_kreditbetrag");
		String runtime = request.getParameter("form_laufzeit");
		
		CreditApplicationBean bean = new CreditApplicationBean();
		bean.setCreditUsage(credit_usage);
		bean.setEmployer(employer);
		bean.setEmploymentType(employment_type);
		bean.setGrossIncome(gross_income);
		bean.setCreditValue(credit_value);
		bean.setRuntime(runtime);
		
		if (request.getSession().getAttribute("customer_id") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("website.jsp");
			rd.forward(request, response);
			return;
		}
		
		bean.setCustomerID((int) request.getSession().getAttribute("customer_id"));
		
		send(bean);
		RequestDispatcher rd = request.getRequestDispatcher("website_after_login.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	// Übermittlung des Privatkreditantrages an die Datenbank
	public void send(CreditApplicationBean bean) {

		Connection connection = null;
		PreparedStatement st = null;
		
		try {
			connection = MySQL.openConnection();
			
			st = connection.prepareStatement("INSERT INTO credit_applications_private (credit_id, employee_id, customer_id, verified, credit_usage, credit_value, runtime, employer, employment_type, gross_income) VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			st.setInt(1, 0);
			st.setInt(2, 0);
			st.setInt(3, bean.getCustomerID());
			st.setBoolean(4, false);
			st.setString(5, bean.getCreditUsage());
			st.setDouble(6, Double.valueOf(bean.getCreditValue()));
			st.setInt(7, Integer.valueOf(bean.getRuntime()));
			st.setString(8, bean.getEmployer());
			st.setString(9, bean.getEmploymentType());
			st.setString(10, bean.getGrossIncome());
			
			st.executeUpdate();

			MySQL.closeRessources(null, st, connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	<div id="kreditRechner"
//			style="position: absolute; bottom: 250px; right: 450px; font-family: Advent Pro; font-size: 20px">
//
//			<p id="rate">Ratenhöhe/Monat:<p>
//			<p id="zinssatz">Zinssatz p .a :</p>
//
//		</div>
}