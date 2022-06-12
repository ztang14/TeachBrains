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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("login.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("user");
		String pswd = request.getParameter("pswd");
		
		RequestDispatcher failure = request.getRequestDispatcher("login.jsp");
		try {
			UserDao udao = new UserDao(DBCon.getConnection());
			User user = udao.userLogin(username, pswd);
			CustomerDao cdao = new CustomerDao(DBCon.getConnection());
			if(user != null) {
			   if(!cdao.customerExist(user.getId())) {
				  Customer customer = cdao.CreateCustomer(user);
				  Card card = cdao.CreateCard(user);
				  customer.setPayment(card);
				  request.getSession().setAttribute("customer", customer);
			    }
				request.getSession().setAttribute("auth", user);
				response.sendRedirect("index.jsp");
			 }else{
				 if(udao.checkAdmin(username, pswd)) {
						
					 request.getSession().setAttribute("auth", user);
					response.sendRedirect("Admin.jsp");
					
				}else {
				 out.print("user login failed");
				 failure.include(request, response);
					}
			 }		
		    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		
	}

}
