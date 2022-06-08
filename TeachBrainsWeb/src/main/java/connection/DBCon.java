package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCon {

	private static Connection con = null;
	private static Statement smt = null;
	private static PreparedStatement psmt = null;
	private static String query = null;
	private static ResultSet rs = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
	  if(con == null) {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TechBrains", "root", "root");
		System.out.print("connected");
	  }
	  return con;
	}
	
	public static int insertData(PreparedStatement psmt)
	{
		
		int result = 0;
		try {
				
			result = psmt.executeUpdate();
			System.out.println("Inserted rows : " + result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
