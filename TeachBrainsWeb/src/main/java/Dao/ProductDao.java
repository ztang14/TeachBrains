package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.DBCon;
import entities.Cart;
import entities.Product;
import entities.User;

public class ProductDao {
	
	private Connection con;
	private PreparedStatement psmt;
	private String query;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<Product> getAllProducts(){
	  List<Product> products = new ArrayList<Product>();
	  
	  try {
		query = "SELECT * FROM products";
		psmt = this.con.prepareStatement(query);
		rs = psmt.executeQuery();
		while(rs.next()) {
			Product row = new Product();
			row.setId(rs.getInt("id"));
			row.setName(rs.getString("name"));
			row.setCategory(rs.getString("category"));
			row.setPrice(rs.getDouble("price"));
			row.setImage(rs.getString("image"));
			
			products.add(row);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size() > 0) {
			  for(Cart item : cartList) {
				query = "select * from products where id=?";
				psmt = this.con.prepareStatement(query);
				psmt.setInt(1, item.getId());
				rs = psmt.executeQuery();
				while(rs.next()) {
					Cart row = new Cart();
					row.setId(rs.getInt("id"));
					row.setName(rs.getString("name"));
					row.setCategory(rs.getString("category"));
					row.setPrice(rs.getDouble("price") * item.getQuantity());
				    row.setQuantity(item.getQuantity());
				    products.add(row);
				}
			  }
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}
	
	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			if(cartList.size() > 0){
				for(Cart item : cartList) {
					query = "select price from products where id=?";
					psmt = this.con.prepareStatement(query);
					psmt.setInt(1, item.getId());
					rs = psmt.executeQuery();
					
					while(rs.next()) {
						sum += rs.getDouble("price") * item.getQuantity();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	public Product getSingleProduct(int id) {
		Product row = null;
		try {
			query = "select * from products where id=?";
			psmt = this.con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				}
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return row;
	}
	
	public boolean addProduct(String name,String category, String img, double price) {
		
		

		query = "INSERT INTO products(name, category, image, price)"
				+ " values(?,?,?,?)";
		try {

		  psmt = this.con.prepareStatement(query);
		 	  
			psmt.setString(1, name);
			psmt.setString(2, category);
			psmt.setString(3, img);
			psmt.setDouble(4, price);
			
			if(DBCon.insertData(psmt) > 0) {
				return true;
			}
		    else {
				return false;
			}
			
		    }catch(Exception e) {
			e.printStackTrace();
		    }

			return false;
	}
}
