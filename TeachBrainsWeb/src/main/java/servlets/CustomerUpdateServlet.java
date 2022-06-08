package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CustomerDao;
import Dao.UserDao;
import connection.DBCon;
import entities.Customer;
import entities.User;

/**
 * Servlet implementation class CustomerUpdateServlet
 */
@WebServlet("/CustomerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
	response.setContentType("text/html");
	
	PrintWriter out = response.getWriter();
	
	User auth = (User) request.getSession().getAttribute("auth");
	
	String username = request.getParameter("user");
	String email = request.getParameter("email");
	String fname = request.getParameter("fname");
	String lname = request.getParameter("lname");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address");
	
	try {
		UserDao udao = new UserDao(DBCon.getConnection());
		CustomerDao cdao = new CustomerDao(DBCon.getConnection());
		
		Customer model = new Customer();
		model.setId(auth.getId());
		model.setUsername(username);
		model.setEmail(email);
		model.setFirstName(fname);
		model.setLastName(lname);
		model.setPhone(phone);
		model.setAddress(address);
		
		
		if(cdao.UpdateCustomer(model)) {
			out.write("Register successfull");
		}else {
			out.write("Register Failed");
		}
		response.sendRedirect("customer.jsp");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}   
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
