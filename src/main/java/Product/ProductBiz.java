package Product;

import java.util.ArrayList;

public class ProductBiz implements IProductBiz{

	private ArrayList<Product> products;
	
	@Override
	public void initialize() {
		products = new ArrayList<Product>();
		// Tambah 2 produk untuk SELLER-0001
		products.add(new Product("PROD-0001", "Uniqlo T-Shirt", "Baju Uniqlo berkualitas tinggi dibuat di Jepang", 100000, 100, "SELLER-0001"));
		products.add(new Product("PROD-0002", "Levi's Jeans", "Celana jeans original Levi's", 250000, 50, "SELLER-0001"));

		// Tambah 2 produk untuk SELLER-0002
		products.add(new Product("PROD-0003", "Sepatu Sneakers", "Sepatu sneakers putih casual", 300000, 75, "SELLER-0002"));
		products.add(new Product("PROD-0004", "Jaket Kulit", "Jaket kulit pria premium", 500000, 40, "SELLER-0002"));

		// Tambah 2 produk untuk SELLER-0003
		products.add(new Product("PROD-0005", "Kemeja Batik", "Kemeja batik formal lengan panjang", 200000, 60, "SELLER-0003"));
		products.add(new Product("PROD-0006", "Celana Chino", "Celana chino slim fit", 180000, 70, "SELLER-0003"));

		// Tambah 2 produk untuk SELLER-0004
		products.add(new Product("PROD-0007", "Blouse Wanita", "Blouse lengan pendek bahan rayon", 120000, 90, "SELLER-0004"));
		products.add(new Product("PROD-0008", "Rok Span", "Rok span untuk kerja", 150000, 55, "SELLER-0004"));

		// Tambah 2 produk untuk SELLER-0005
		products.add(new Product("PROD-0009", "Kaos Polo", "Kaos polo pria lengan pendek", 130000, 80, "SELLER-0005"));
		products.add(new Product("PROD-0010", "Sweater Rajut", "Sweater rajut tebal untuk musim dingin", 220000, 45, "SELLER-0005"));
				
	}

	@Override
	public void printAllProduct() {
		for (Product product : products) {			
			System.out.println(product.toStringList());
		}
	}
	
	@Override
	public Product getProductById(String productId) {
		for (Product product : products) {
			if(product.getId().equals(productId)) {				
				return product;
			}
		}
		return null;
	}

	@Override
	public void addProduct() {
		
	}

	@Override
	public void editProduct(Product product) {
		
	}

	@Override
	public void deleteProductbyId(String productId) {
		
	}
}
