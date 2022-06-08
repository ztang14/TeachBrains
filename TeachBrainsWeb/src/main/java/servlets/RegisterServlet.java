package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserDao;
import connection.DBCon;




public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    }
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("user");
		String pswd = request.getParameter("pswd");
		String email = request.getParameter("email");
		String cfpswd = request.getParameter("cfpswd");
		
		RequestDispatcher success = request.getRequestDispatcher("login.jsp");
		
		RequestDispatcher failure = request.getRequestDispatcher("register.jsp");
        
		try {
			UserDao udao = new UserDao(DBCon.getConnection());
			
			if(udao.userRegister(email, username, pswd, cfpswd)) {
				out.write("Register successfull");
				success.include(request, response);
			}else {
				out.write("Register Failed");
				failure.include(request, response);
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
