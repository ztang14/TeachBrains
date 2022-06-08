package connection;

import java.sql.SQLException;
import java.util.List;

import Dao.ProductDao;
import Dao.UserDao;
import entities.Product;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String username = "user";
		String pswd = "pswd";
		String email = "email";
		String cfpswd = "cfpswd";
		
		try {
			UserDao udao = new UserDao(DBCon.getConnection());
			
			if(udao.userRegister(email, username, pswd, cfpswd)) {
				System.out.print("Register successfull");
			}else {
				System.out.print("Register Failed");
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
