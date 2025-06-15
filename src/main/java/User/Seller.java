package User;

public class Seller extends User{
	private String storeName;
	private String storeAddress;
//	private Produk 
	
	public Seller(String id, String name, String email, String password, String storeName, String storeAddress) {
		super(id, name, email, password);
		this.storeName= storeName;
		this.storeAddress = storeAddress;
	}
}
