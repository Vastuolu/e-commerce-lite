package Cart;

import java.util.ArrayList;

import Product.Product;
import Util.CommonUtil;

public class Cart {
	private ArrayList<CartItem> items;
	
	public Cart() {
		this.items = new ArrayList<CartItem>();
	}
	
	public void addItem(Product product, int quantity) {
		for (CartItem item : items) {
			if(item.getProduct().getId().equals(product.getId())) {
				item.increaseQuantity(1);
				return;
			}
		}
		items.add(new CartItem(product, quantity));
	}
	
	public void removeItem(int itemIndex) {
		items.remove(itemIndex);
	}
	
	public void increaseItem(int itemIndex, int quantity) {
		items.get(itemIndex).increaseQuantity(quantity);
	}
	
	public void decreaseItem(int itemIndex, int quantity) {
		items.get(itemIndex).decreaseQuantity(quantity);
	}
	
	public CartItem getItemByIndex(int index) {
		return items.get(index);
	}
	
	public int getItemIndexById(String productId) {
		for (int i = 0 ; i < items.size(); i++) {
			if(items.get(i).getProduct().getId().equals(productId)) {
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<CartItem> getItems() {
		return items;
	}

	public int getTotalCart() {
		int total = 0;
		for (CartItem cartItem : items) {
			total += cartItem.getSubTotal();
		}
		return total;
	}
	
	public void printAllitems() {
		if(items.size() == 0) System.out.println("\t<<Keranjang Kosong>>");
		for (CartItem item : items) {
			System.out.println(item.toString());
		}
	}
}
