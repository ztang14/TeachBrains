package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBCon;
import entities.Card;
import entities.Customer;
import entities.Product;
import entities.User;

public class CustomerDao {

	private Connection con;
	private PreparedStatement psmt;
	private String query;
	private ResultSet rs;

	
	public CustomerDao(Connection con) {
		this.con = con;
	}
	
	public Customer CreateCustomer(User user) {
		
		Customer model = null;
		try {
			
			query = "INSERT INTO customers(id)"
					+ " values(?)";

		    psmt = this.con.prepareStatement(query);
			 	  
			psmt.setInt(1, user.getId());
			
				
				if(DBCon.insertData(psmt) > 0) {
						  model = new Customer();
						  model.setId(user.getId());
						  return model;				
				}
			    else {
					return model;
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	public Card CreateCard(User user) {
		
		Card model = null;
		try {
			
			query = "INSERT INTO cards(id)"
					+ " values(?)";

		    psmt = this.con.prepareStatement(query);
			 	  
			psmt.setInt(1, user.getId());
			
				
				if(DBCon.insertData(psmt) > 0) {
						  model = new Card();
						  model.setId(user.getId());
						  return model;
					
				}
			    else {
					return model;
				}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	public boolean UpdateCard(Customer customer) {
		boolean result = false;
       try {
			
			query = "update cards set number=?, firstName=?, lastName=?, expiredDate=? ,billAddress=?, cvv=? where id=?";
			psmt = this.con.prepareStatement(query);
			psmt.setInt(1, customer.getPayment().getNumber());
			psmt.setString(2, customer.getPayment().getFirstName());
			psmt.setString(3, customer.getPayment().getLastName());
			psmt.setString(4, customer.getPayment().getExpiredDate());
			psmt.setString(5, customer.getPayment().getBillAddress());
			psmt.setInt(6, customer.getPayment().getCvv());
			psmt.setInt(7, customer.getPayment().getId());
			psmt.executeUpdate();
			result = true;
		
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean UpdateCustomer(Customer customer) {
		
		boolean result = false;
		
		try {
			
			query = "update customers set phone=?, firstName=?, lastName=?, address=? where id=?";
			
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
	
	public boolean customerExist(int id) {
		query = "select 1 from customers where id = ? limit 1";
		try {

			  psmt = this.con.prepareStatement(query);
			 	  
			  psmt.setInt(1, id);
			  
			  rs = psmt.executeQuery();
			  
			  if(rs.next()) {
				  return true;
			  }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean cardExist(int id) {
		query = "select 1 from cards where id = ? limit 1";
		try {

			  psmt = this.con.prepareStatement(query);
			 	  
			  psmt.setInt(1, id);
			  
			  rs = psmt.executeQuery();
			  
			  if(rs.next()) {
				  return true;
			  }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Customer getCustomer(int id) {
		Customer row = new Customer();
		try {
			query = "SELECT * FROM customers where id=?";
			psmt = this.con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				row.setId(rs.getInt("id"));
				row.setFirstName(rs.getString("firstName"));
				row.setLastName(rs.getString("lastName"));;
				row.setPhone(rs.getString("phone"));
				row.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
		
	}
	public Card getCard(int id) {
		Card row = new Card();
		try {
			query = "SELECT * FROM cards where id=?";
			psmt = this.con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				row.setId(rs.getInt("id"));
				row.setNumber(rs.getInt("number"));
				row.setFirstName(rs.getString("firstName"));
				row.setLastName(rs.getString("lastName"));;
				row.setExpiredDate(rs.getString("expiredDate"));
				row.setBillAddress(rs.getString("billAddress"));
				row.setCvv(rs.getInt("cvv"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
	public List<Customer> getAllCustomer(){
		  List<Customer> customers = new ArrayList<Customer>();
		  
		  try {
			query = "SELECT * FROM customers";
			psmt = this.con.prepareStatement(query);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Customer row = new Customer();
				row.setId(rs.getInt("id"));
				row.setFirstName(rs.getString("firstName"));
				row.setLastName(rs.getString("lastName"));
				row.setAddress(rs.getString("address"));
				row.setPhone(rs.getString("phone"));
				
				customers.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return customers;
		}
}
