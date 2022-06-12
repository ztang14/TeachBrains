package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CustomerDao;
import Dao.OrderDao;
import connection.DBCon;
import entities.Card;
import entities.Cart;
import entities.Order;
import entities.User;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cvv = Integer.parseInt(request.getParameter("cvv"));
		RequestDispatcher failure = request.getRequestDispatcher("checkout.jsp");
		
		try(PrintWriter out = response.getWriter()){
			
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			

			
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			User auth = (User) request.getSession().getAttribute("auth");
			CustomerDao cdao = new CustomerDao(DBCon.getConnection());
		    Card card = new Card();
		    card = cdao.getCard(auth.getId());
            int mycvv = card.getCvv();
            
            if(mycvv == cvv) {
			  if(cart_list != null && auth != null) {
				
				for(Cart c :cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					OrderDao oDao = new OrderDao(DBCon.getConnection());
					boolean result = oDao.insertOrder(order);
					if(!result) break;
				 }
				
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			  }else {
				if(auth == null) response.sendRedirect("login.jsp");
				response.sendRedirect("cart.jsp");
			  }
            }else {
            	out.write("Your cvv is wrong, the transaction is terminated");
            	failure.include(request, response);
            }
		
		}catch(Exception e){ 
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
