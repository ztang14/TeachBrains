package com.onlineShop.entities;

public class Order {
	
	private int id;

	private Cart cart;

	private Customer customer;

	private ShippingAddress shippingAddress;

	private BillAddress billingAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public BillAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillAddress billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	
}
