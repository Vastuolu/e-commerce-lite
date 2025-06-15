package Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import Cart.Cart;
import Cart.CartItem;
import Main.Main;
import Product.Product;
import Product.ProductBiz;
import User.Customer;
import User.Seller;
import User.User;
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
				}else if(quantity > product.getStock()) {
					throw new Exception("[Error] Input kuantitas tidak boleh lebih atau sama dari kuantitas item yang dipilih.");						
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
				header("List Keranjang");
				userCart.printAllitems();
				footer();
				System.out.println("1. Tambah Kuantitas | 3. Hapus Produk \n2. Kurangi Kuantitas | 0. Kembali");
				footerChoice();
				pilihan = Integer.parseInt(CommonUtil.getUserInput());
				
				if(pilihan == 0) {
					return;
				}else if(pilihan != 1 && pilihan != 2 && pilihan != 3){
					throw new Exception("[Error] Pilihan Tidak ada. Silahkan Masukkan angka yang benar");
				}
				
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

	public static void checkoutMenu(UserBiz uBiz) {
		Cart userCart = ((Customer) Main.userCookies).getCart();
		if(userCart.getItems().size() == 0) {
			header("Checkout Barang Keranjang");
			System.out.println("<<Keranjang kosong. Tidak ada Checkout>>");
			footer();
			System.out.println("Tekan Enter Untuk Kembali ke menu beranda....");
			CommonUtil.getUserInput();
			CommonUtil.clearConsole();
			return;
		}
		ArrayList<Integer> jenisAntar = new ArrayList<Integer>();
		jenisAntar.add(8000);
		jenisAntar.add(9500);
		jenisAntar.add(22000);
		jenisAntar.add(14200);
		int pilihan = -1;
		while(pilihan != 0) {
			try {
				header("Checkout Barang Keranjang");
				System.out.println("Nama Penerima: "+Main.userCookies.getName());
				System.out.println("Alamat Pengantaran: "+((Customer)Main.userCookies).getAddress());
				System.out.println("No Telp Penerima: "+((Customer)Main.userCookies).getPhoneNumber());
				footer();
				userCart.printAllitems();
				footer();
				System.out.println("Total Seluruh Barang: "+CommonUtil.moneyFormat(userCart.getTotalCart()));
				footer();
				System.out.println("1. Checkout | 0. Kembali");
				footerChoice();
				pilihan = Integer.parseInt(CommonUtil.getUserInput());
				
				if(pilihan == 0) {
					return;
				}else if(pilihan != 1){
					throw new Exception("[Error] Pilihan Tidak ada. Silahkan Masukkan angka yang benar");
				}
				
				System.out.println(">1. Ekonomi");
				System.out.println(">2. Standard");
				System.out.println(">3. Kargo");
				System.out.println(">4. Reguler");
				System.out.print(">>Pilih Jenis Pengantaran(1/2/3/4): ");
				int typeChoose = Integer.parseInt(CommonUtil.getUserInput());
				if(typeChoose != 1 && typeChoose != 2 && typeChoose != 3 && typeChoose != 4 ) {				
					throw new Exception("[Error] Pilihan jenis pengantaran antara 1/2/3/4.");
				}

				CommonUtil.clearConsole();
				if(checkoutFinal(jenisAntar.get(typeChoose-1), uBiz)) return;

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
	
	private static boolean checkoutFinal(int delivPrice, UserBiz uBiz) {
		Cart userCart = ((Customer) Main.userCookies).getCart();
		int pilihan = -1;
		while(pilihan != 0) {
			try {
				int total = userCart.getTotalCart()+delivPrice;
				header("Pembayaran Barang Checkout");
				System.out.println("Nama Penerima: "+Main.userCookies.getName());
				System.out.println("Alamat Pengantaran: "+((Customer)Main.userCookies).getAddress());
				System.out.println("No Telp Penerima: "+((Customer)Main.userCookies).getPhoneNumber());
				footer();
				userCart.printAllitems();
				footer();
				System.out.println("Total Seluruh Barang: "+CommonUtil.moneyFormat(userCart.getTotalCart()));
				System.out.println("Biaya Pengiriman: "+CommonUtil.moneyFormat(delivPrice));
				System.out.println("Total Pembayaran: "+CommonUtil.moneyFormat(total));
				footer();
				System.out.println("1. Bayar | 0. Kembali");
				footerChoice();
				pilihan = Integer.parseInt(CommonUtil.getUserInput());
				
				if(pilihan == 0) {
					return false;
				}else if(pilihan != 1){
					throw new Exception("[Error] Pilihan Tidak ada. Silahkan Masukkan angka yang benar");
				}

				System.out.print(">>Masukkan Nominal Pembayaran Anda: ");
				int nominal = Integer.parseInt(CommonUtil.getUserInput());
				if(nominal < total) {
					throw new Exception("[Error] Nominal yang anda masukkan kurang.");
				}else if(nominal > total) {
					System.out.println("@Nominal lebih akan dikembalikan ke rekening anda.");
				}
				
				for (int i = 0; i < userCart.getItems().size(); i++) {
					Product product = userCart.getItemByIndex(i).getProduct();
					User user= uBiz.getUserById(product.getSellerId());
					((Seller)user).decreaseStock(product.getId(), userCart.getItemByIndex(i).getQuantity());
				}
				userCart.getItems().clear();
				
				System.out.println("@Pembayaran berhasil. Ditunggu ya paketnya...");
				TimeUnit.SECONDS.sleep(2);
				CommonUtil.clearConsole();
				break;
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
		return true;
	}
}
