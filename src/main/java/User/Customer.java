package User;

import java.util.ArrayList;

public class Customer extends User{
	private String address;
	private String phoneNumber;
	private Cart cart;
	private ArrayList<Product> wishlist;
	
	public Customer(String id, String name, String email, String password) {
		super(id, name, email, password);
	}
	
	
}
