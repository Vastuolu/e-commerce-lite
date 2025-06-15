package Product;

import java.util.ArrayList;

import Util.CommonUtil;

public class Product {
	private String id;
	private String name;
	private int price;
	private int stock;
	private String sellerId;
	private ArrayList<Product> products;

	public Product(String id, String name, int price, int stock, String sellerId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.sellerId = sellerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public void addProduct() {
		System.out.print(">>Masukkan Email: ");
		String inEmail = CommonUtil.getUserInput();  
//		if(!inEmail.matches(emailRegex)) {				
//			throw new Exception("[Error] Format Email Salah.");
//		}
	}

	public void editProduct(Product product) {
		
	}

	public void deleteProductbyId(String productId) {
		
	}

	public String toString() {
		return id + "\t" + name + "\t" + CommonUtil.moneyFormat(price)+ "\tStok: " + stock;
	}
	
	
}
