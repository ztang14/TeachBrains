package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public boolean userExist(String username) {
		query = "select 1 from login where username = ? limit 1";
		try {

			  psmt = this.con.prepareStatement(query);
			 	  
			  psmt.setString(1, username);
			  
			  rs = psmt.executeQuery();
			  
			  if(rs.next()) {
				  return true;
			  }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getUserId(String username) {

		int id = -1;
		try {
		  query = "SELECT * FROM login where username = ?";
		  psmt = this.con.prepareStatement(query);
		  psmt.setString(1, username);
		  rs = psmt.executeQuery();
		  
		  if(rs.next()) {
			  id = rs.getInt("id");
		  }
	    }catch(Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return id;
	}
	
	public boolean checkAdmin(String username, String pswd) {
		if(username.equals("admin") && pswd.equals("admin")) {
			return true;
		}
		return false;
	}
}
