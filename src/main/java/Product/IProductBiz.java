package Product;

public interface IProductBiz {
	public void initialize();
	public Product getProductById(String productId);
	public void printAllProduct();
	public void addProduct();
	public void editProduct(Product product);
	public void deleteProductbyId(String productId);
}
