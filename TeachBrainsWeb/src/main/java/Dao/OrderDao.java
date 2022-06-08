package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Order;
import entities.Product;

public class OrderDao {

	private Connection con;
	private PreparedStatement psmt;
	private String query;
	private ResultSet rs;
	
	public OrderDao(Connection con) {
		this.con = con;
	}
	
	public boolean insertOrder(Order model) {
		
		boolean result = false;
		
		try {
			
			query = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
			
			psmt = this.con.prepareStatement(query);
			psmt.setInt(1, model.getId());
			psmt.setInt(2, model.getUid());
			psmt.setInt(3, model.getQuantity());
			psmt.setString(4, model.getDate());
			psmt.executeUpdate();
			result = true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public List<Order> userOrders(int id){
		List<Order> list = new ArrayList<>();
		try {
			query = "select * from orders where u_id=? order by orders.o_id desc";
			psmt = this.con.prepareStatement(query);
			psmt.setInt(1, id);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Order order = new Order();
				ProductDao productDao = new ProductDao(this.con);	
				int pId = rs.getInt("p_id");
				
				Product product = productDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("o_id"));
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void cancelOrder(int id) {
		try {
			query = "delete from orders where o_id=?";
			psmt = this.con.prepareStatement(query);
			psmt.setInt(1, id);
			psmt.execute();
		}catch(Exception e) {
			
		}
	}
}
