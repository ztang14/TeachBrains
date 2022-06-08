package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DBCon;
import entities.User;

public class UserDao {

	private Connection con;
	private PreparedStatement psmt;
	private String query;
	private ResultSet rs;
	
	public UserDao(Connection con) {
		super();
		this.con = con;
	}
	
	public User userLogin(String username, String password) {
		
		User user = null;
		
		try {
		  query = "SELECT * FROM login where username = ? and password=?";
		  psmt = this.con.prepareStatement(query);
		  psmt.setString(1, username);
		  psmt.setString(2, password);
		  rs = psmt.executeQuery();
		  
		  if(rs.next()) {
			  user = new User();
			  user.setId(rs.getInt("id"));
			  user.setUsername(rs.getString("username"));
			  user.setPassword(rs.getString("password"));
			  user.setEmail(rs.getString("email"));
		  }
		}catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}
	
	public boolean userRegister(String email,String username, String password,String cfPassword) {
		
		
		if(password.equals(cfPassword))
		{
			User user = new User();
			user.setEmail(email);
			user.setUsername(username);
			user.setPassword(password);
			System.out.print(email + username);
			
	    query = "INSERT INTO login(username, password, email)"
				+ " values(?,?,?)";
		try {

		  psmt = this.con.prepareStatement(query);
		 	  
			psmt.setString(1, user.getUsername());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getEmail());
			
			if(DBCon.insertData(psmt) > 0) {
				return true;
			}
		    else {
				return false;
			}
			
		    }catch(Exception e) {
			e.printStackTrace();
		    }
		}
			return false;
	}
	
}
