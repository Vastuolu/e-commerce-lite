package User;

import java.util.ArrayList;

import Cart.Cart;
import Product.Product;

public class Customer extends User{
	private String address;
	private String phoneNumber;
	private Cart cart;
	
	public Customer(String id, String name, String email, String password, String address, String phoneNumber) {
		super(id, name, email, password);
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.cart = new Cart();
	}

	public Cart getCart() {
		return cart;
	}

	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}	
	
	
	
}
