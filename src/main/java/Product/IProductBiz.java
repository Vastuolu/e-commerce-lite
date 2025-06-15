package Product;

import User.UserBiz;

public interface IProductBiz {
	public void refreshProductBiz(UserBiz uBiz);
	public Product getProductById(String productId);
	public void printAllProduct();
}
