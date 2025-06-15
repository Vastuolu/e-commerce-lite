package Page;

import java.util.concurrent.TimeUnit;

import Cart.Cart;
import Main.Main;
import Product.Product;
import Product.ProductBiz;
import User.Customer;
import User.UserBiz;
import Util.CommonUtil;

public class CustomerPage extends Page{
	
	public static void berandaMenu() {
		header("E-Commerce Lite");
		System.out.println("1. Cari Produk");
		System.out.println("2. Lihat Keranjang");		
		System.out.println("3. Check Out Keranjang");		
		System.out.println("9. Log Out");		
		footerChoice();
	}
	
	public static void listProductMenu(ProductBiz productsBiz, UserBiz usersBiz){
		int pilihan = -1;
		while(pilihan != 0) {
			try {				
				header("Produk List");
				productsBiz.printAllProduct();
				footer();
				System.out.println("1. Masukkan Keranjang | 0. Kembali");
				footerChoice();
				pilihan = Integer.parseInt(CommonUtil.getUserInput());
				
				if(pilihan == 0) {
					return;
				}else if(pilihan != 1){
					throw new Exception("[Error] Pilihan Tidak ada. Silahkan Masukkan angka yang benar");
				}
				
				System.out.print(">>Masukkan ID Produk: ");
				String productId = CommonUtil.getUserInput();
				if(productId.isEmpty()) {
					throw new Exception("[Error] Id Produk tidak boleh kosong.");
				}
				
				Product product = productsBiz.getProductById(productId);
				if(product == null) {
					throw new Exception("[Error] Produk dengan ID \""+productId+"\" tidak ada.");
				}
				
				System.out.print(">>Masukkan kuantitas: ");
				int quantity = Integer.parseInt(CommonUtil.getUserInput());
				if(quantity <= 0) {
					throw new Exception("[Error] Input kuantitas tidak boleh <= 0.");
				}
				
				String userId = Main.userCookies.getId();
				((Customer) usersBiz.getUserById(userId)).getCart().addItem(product, quantity);
				System.out.println("@Produk berhasil ditambahkan ke keranjang...");				
				TimeUnit.SECONDS.sleep(2);
				CommonUtil.clearConsole();
			} catch (NumberFormatException e) {
				CommonUtil.clearConsole();
				System.out.println("[Error] Input harus berupa angka");
				pilihan = -1;
				continue;
			} catch (Exception e) {
				CommonUtil.clearConsole();
				System.out.println(e.getMessage());
				pilihan = -1;
				continue;
			}
		}
		
	}

	public static void cartMenu() {
		int pilihan = -1;
		Cart userCart = ((Customer) Main.userCookies).getCart();
		while(pilihan != 0) {
			try {
				header("Produk List");
				userCart.printAllitems();
				footer();
				System.out.println("1. Tambah Kuantitas | 3. Hapus Produk \n2. Kurangi Kuantitas | 0. Kembali");
				footerChoice();
				pilihan = Integer.parseInt(CommonUtil.getUserInput());
				
				if(pilihan == 0) return;
				
				System.out.print(">>Masukkan ID Produk: ");
				String productId = CommonUtil.getUserInput();
				if(productId.isEmpty()) {
					throw new Exception("[Error] Id Produk tidak boleh kosong.");
				}

				int itemIndex = userCart.getItemIndexById(productId);
				if(itemIndex == -1) {
					throw new Exception("[Error] Produk dengan ID \""+productId+"\" tidak ada dalam keranjang.");						
				}

				if(pilihan == 1 || pilihan == 2) {
					System.out.print(">>Masukkan kuantitas: ");
					int quantity = Integer.parseInt(CommonUtil.getUserInput());
					
					if(pilihan == 1) {
						userCart.increaseItem(itemIndex, quantity);
						System.out.println("@Kuantitas berhasil ditambahkan...");
					}else {
						if(quantity <= 0 ) {
							throw new Exception("[Error] Kuantitas tidak boleh <= 0.");
						}else if(quantity >= userCart.getItemByIndex(itemIndex).getQuantity()) {
							throw new Exception("[Error] Input kuantitas tidak boleh lebih atau sama dari kuantitas item yang dipilih.");						
						}
						userCart.decreaseItem(itemIndex, quantity);
						System.out.println("@Kuantitas berhasil dikurangi...");						
					}
				}else if(pilihan == 3) {
					userCart.removeItem(itemIndex);
					System.out.println("@Item Berhasil Dihapus...");						
				}
				TimeUnit.SECONDS.sleep(2);
				CommonUtil.clearConsole();
			} catch (NumberFormatException e) {
				CommonUtil.clearConsole();
				System.out.println("[Error] Input harus berupa angka");
				pilihan = -1;
				continue;
			} catch (Exception e) {
				CommonUtil.clearConsole();
				System.out.println(e.getMessage());
				pilihan = -1;
				continue;
			}
		}
	}
}
