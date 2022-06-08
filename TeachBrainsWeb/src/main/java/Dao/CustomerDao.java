package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import entities.Customer;

public class CustomerDao {

	private Connection con;
	private PreparedStatement psmt;
	private String query;

	
	public CustomerDao(Connection con) {
		this.con = con;
	}
	
	public boolean UpdateCustomer(Customer customer) {
		
		boolean result = false;
		
		try {
			
			query = "update customers set phone=?, firstName=?, lastName=?, address=?, where id=?";
			
			psmt = this.con.prepareStatement(query);
			psmt.setString(1, customer.getPhone());
			psmt.setString(2, customer.getFirstName());
			psmt.setString(3, customer.getLastName());
			psmt.setString(4, customer.getAddress());
			psmt.setInt(5, customer.getId());
			psmt.executeUpdate();
			result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
}
