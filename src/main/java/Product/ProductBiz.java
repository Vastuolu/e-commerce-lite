package Product;

import java.util.ArrayList;
import java.util.HashMap;

import Main.Main;
import User.Customer;
import User.Seller;
import User.User;
import User.UserBiz;
import Util.CommonUtil;

public class ProductBiz implements IProductBiz{

	private ArrayList<Product> products;
	
	public void refreshProductBiz(UserBiz uBiz) {
		products = new ArrayList<Product>();
		HashMap<String, User> users =  uBiz.getAllUser();
		for (String id : users.keySet()) { 
			if(users.get(id) instanceof Customer) continue;
			for (Product sellerProd : ((Seller) users.get(id)).getProducts() ) {				
				products.add(sellerProd);
			}
		}		
	}

	@Override
	public void printAllProduct() {
		for (Product product : products) {			
			System.out.println(product.toString());
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
}
