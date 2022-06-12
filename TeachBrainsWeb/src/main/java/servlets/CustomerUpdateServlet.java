package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CustomerDao;
import Dao.UserDao;
import connection.DBCon;
import entities.Card;
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
	
	RequestDispatcher result = request.getRequestDispatcher("customers.jsp");
	
	User auth = (User) request.getSession().getAttribute("auth");
	
	String username = request.getParameter("user");
	String email = request.getParameter("email");
	String fname = request.getParameter("fname");
	String lname = request.getParameter("lname");
	String phone = request.getParameter("phone");
	String address = request.getParameter("address");
	
	int number = Integer.parseInt(request.getParameter("number"));
	String expiredDate = request.getParameter("cexdate");
	String cfname = request.getParameter("cfname");
	String clname = request.getParameter("clname");
	int cvv = Integer.parseInt(request.getParameter("cvv"));
	String billAddress = request.getParameter("billAddress");
	try {
		UserDao udao = new UserDao(DBCon.getConnection());
		CustomerDao cdao = new CustomerDao(DBCon.getConnection());
		
		Customer model = new Customer();
		Card card = new Card();
		
		model.setId(auth.getId());
		model.setUsername(username);
		model.setEmail(email);
		model.setFirstName(fname);
		model.setLastName(lname);
		model.setPhone(phone);
		model.setAddress(address);
		
		card.setId(auth.getId());
		card.setNumber(number);
		card.setFirstName(cfname);
		card.setLastName(clname);
        card.setExpiredDate(expiredDate);
        card.setBillAddress(billAddress);
        card.setCvv(cvv);
        
        model.setPayment(card);
		
		
		if(cdao.UpdateCustomer(model)) {
			if(cdao.UpdateCard(model)) {
			  out.write("Update successfull");
			  result.include(request, response);
			}
		}else {
			out.write("Update Failed");
			result.include(request, response);
		}
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
