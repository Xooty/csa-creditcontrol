package de.hwrberlin.creditcontrol.creditapplications;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.hwrberlin.creditcontrol.login.UserBean;
import de.hwrberlin.creditcontrol.mysql.MySQL;

public class CreditMortageApplicationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;  charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter printWriter = response.getWriter();
		
		String property_type = request.getParameter("form_verwendungszweck");
		String employer = request.getParameter("form_arbeitgeber");
		String employment_type = request.getParameter("form_verhaeltnis");
		String gross_income = request.getParameter("form_bruttoeinkommen");
		String credit_value = request.getParameter("form_kreditbetrag");
		String runtime = request.getParameter("form_laufzeit");
		String address = request.getParameter("form_adresse");
		
		CreditApplicationBean bean = new CreditApplicationBean();
		bean.setPropertyType(property_type);
		bean.setEmployer(employer);
		bean.setEmploymentType(employment_type);
		bean.setGrossIncome(gross_income);
		bean.setCreditValue(credit_value);
		bean.setRuntime(runtime);
		bean.setAddress(address);
		if (request.getSession().getAttribute("customer_id") == null) {
			RequestDispatcher rd = request.getRequestDispatcher("website.jsp");
			rd.forward(request, response);
			return;
		}
		bean.setCustomerID((int) request.getSession().getAttribute("customer_id"));

		if (send(bean)) {
			request.getSession().setAttribute("credit_property_type_mortage", bean.getPropertyType());
			request.getSession().setAttribute("credit_value_mortage", bean.getCreditValue() + " €");
			request.getSession().setAttribute("credit_runtime_mortage", bean.getRuntime() + " Monate");
			request.getSession().setAttribute("credit_verified_mortage", bean.isVerified());
			request.getSession().setAttribute("credit_address_mortage", bean.getAddress());
			request.getSession().setAttribute("credit_rate_mortage", (Integer.valueOf(bean.getCreditValue()) / Integer.valueOf(bean.getRuntime())) + " € / Monat");
			RequestDispatcher rd = request.getRequestDispatcher("website_after_login.jsp");
			rd.forward(request, response);
		} else {
			printWriter.println("Sie haben bereits einen Baufinanzierungskredit angefragt!");
			RequestDispatcher rd = request.getRequestDispatcher("credit_mortage.jsp");
			rd.include(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public boolean send(CreditApplicationBean bean) {

		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			connection = MySQL.openConnection();
			
			st = connection.prepareStatement("SELECT * FROM credit_applications_mortage WHERE customer_id = ?");
			st.setInt(1, bean.getCustomerID());
			
			rs = st.executeQuery();
			
			if (rs.first()) {
				return false;
			}
			
			rs.close();
			st.close();
			
			st = connection.prepareStatement("INSERT INTO credit_applications_mortage (employee_id, customer_id, verified, property_type, credit_value, runtime, employer, employment_type, gross_income, address) VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			st.setInt(1, 0);
			st.setInt(2, bean.getCustomerID());
			st.setBoolean(3, false);
			st.setString(4, bean.getPropertyType());
			st.setDouble(5, Double.valueOf(bean.getCreditValue()));
			st.setInt(6, Integer.valueOf(bean.getRuntime()));
			st.setString(7, bean.getEmployer());
			st.setString(8, bean.getEmploymentType());
			st.setString(9, bean.getGrossIncome());
			st.setString(10, bean.getAddress());
			
			st.executeUpdate();

			MySQL.closeRessources(null, st, connection);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}