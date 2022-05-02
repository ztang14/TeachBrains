package com.onlineShop.entities;

public class Customer {
	
  private int id;
  
  private String firstName;
  
  private String lastName;
  
  private String email;
  
  private String phone;
  
  private String billaddress;
  
  private String shippingaddress;
  
  private User user;
  
  private Cart cart;
  
  
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getBilladdress() {
	return billaddress;
}

public void setBilladdress(String billaddress) {
	this.billaddress = billaddress;
}

public String getShippingaddress() {
	return shippingaddress;
}

public void setShippingaddress(String shippingaddress) {
	this.shippingaddress = shippingaddress;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public Cart getCart() {
	return cart;
}

public void setCart(Cart cart) {
	this.cart = cart;
}

}
