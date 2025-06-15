package User;

import java.util.ArrayList;

import Product.Product;

public class Seller extends User{
	private String storeName;
	private String storeAddress;
	private ArrayList<Product> products;
	
	public Seller(String id, String name, String email, String password, String storeName, String storeAddress) {
		super(id, name, email, password);
		this.storeName= storeName;
		this.storeAddress = storeAddress;
		this.products = new ArrayList<Product>();
	}

	public String getStoreName() {
		return storeName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public Product getProductById(String id) {
		for (Product product : products) {
			if(product.getId().equals(id)) {
				return product;
			}
		}
		return null;
	}
	
	public void addProduct(String name, int price, int stock) {
		String productId = "SR-"+getId().split("-")[1]+"-PD-"+products.size();
		products.add(new Product(productId, name, price, stock, getId()));
	}
	
	public void editProduct(Product product) {
		for (int i = 0 ; i < products.size(); i++) {
			if(products.get(i).getId().equals(product.getId())) {
				products.set(i, product);
				return;
			}
		}
	}
	
	public void deleteProductById(String id) {
		for (int i = 0 ; i < products.size(); i++) {
			if(products.get(i).getId().equals(id)) {
				products.remove(i);
				return;
			}
		}
	}
	
	public void decreaseStock(String id, int quantity) throws Exception{
		for (Product product : products) {
			if(product.getId().equals(id)) {
				if(quantity > product.getStock()) {
					throw new Exception("[Error] Kuantitas barang melebihi stok yang tersedia. Silahkan kurangi.");
				}
				product.setStock(product.getStock()-quantity);
				if(product.getStock() == 0) deleteProductById(id);
			}
		}
	}
}
