package Product;

import Util.CommonUtil;

public class Product {
	private String id;
	private String name;
	private String desc;
	private int price;
	private int stock;
	private String sellerId;

	public Product(String id, String name, String desc, int price, int stock, String sellerId) {
		this.id = id;
		this.name = name;
		this.desc = desc;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	
	public String toStringDetail() {
		return "ID: " + id + "\nNama: " + name + "\nDeskripsi: " + desc + "\nHarga: " + CommonUtil.moneyFormat(price)+ "\nStok: " + stock;
	}

	public String toStringList() {
		return id + "\t" + name + "\t" + CommonUtil.moneyFormat(price)+ "\nStok: " + stock;
	}
	
	
}
