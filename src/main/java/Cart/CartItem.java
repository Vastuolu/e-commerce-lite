package Cart;

import Product.Product;
import Util.CommonUtil;

public class CartItem {
	private Product product;
	private int quantity;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public int getSubTotal() { 
		return product.getPrice()*quantity;
	}

	public void increaseQuantity(int quantity) {
		this.quantity += quantity;
	}

	
	public void decreaseQuantity(int quantity) {		
		this.quantity -= quantity;
	}

	@Override
	public String toString() {
		return product.getId()+"\t"+product.getName()+"\t[Quantity]: "+ 
				quantity+"\t[SubTotal]: "+CommonUtil.moneyFormat(getSubTotal());
	}
	
	
}
