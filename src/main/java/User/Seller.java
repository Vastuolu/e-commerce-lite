package User;

public class Seller extends User{
	private String address;
	private String phoneNumber;
//	private Cart cart;
//	private ArrayList<Product> wishlist;
	
	public Seller(String id, String name, String email, String password, String storeName, String storeAddress) {
		super(id, name, email, password);
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
}
