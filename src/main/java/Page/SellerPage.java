package Page;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Main.Main;
import Product.Product;
import Product.ProductBiz;
import User.Seller;
import User.UserBiz;
import Util.CommonUtil;

public class SellerPage extends Page{
	public static void berandaMenu() {
		header(((Seller)Main.userCookies).getStoreName());
		System.out.println("1. List Produk Toko");
		System.out.println("2. Tambah Produk");		
		System.out.println("3. Edit Produk");		
		System.out.println("4. Hapus Produk");		
		System.out.println("9. Log Out");		
		footerChoice();
	}
	
	public static void listOwnedProduct() {
		Seller seller = ((Seller) Main.userCookies);
		header("List Produk "+seller.getStoreName());
		for (Product product : seller.getProducts()) {
			System.out.println(product.toString());
		}
		footer();
		System.out.println("Tekan Enter Untuk Kembali ke menu beranda....");
		CommonUtil.getUserInput();
		CommonUtil.clearConsole();
	}
	
	public static void addProduct(ProductBiz pBiz) {
		try {
			
			Seller seller = ((Seller) Main.userCookies);
			header("Menambah Produk Toko");
			
			System.out.print(">>Masukkan Nama Produk: ");
			String productName = CommonUtil.getUserInput();
			if(productName.isEmpty()) {
				throw new Exception("[Error] Nama produk tidak boleh kosong.");
			}

			System.out.print(">>Masukkan Harga Produk: ");
			int productPrice = Integer.parseInt(CommonUtil.getUserInput());
			if(productPrice <= 0) {
				throw new Exception("[Error] Harga produk tidak boleh kurang dari atau sama dengan 0.");
			}
			
			System.out.print(">>Masukkan Stok Produk: ");
			int productStock = Integer.parseInt(CommonUtil.getUserInput());
			if(productStock <= 0) {
				throw new Exception("[Error] Stock produk tidak boleh kurang dari atau sama dengan 0.");
			}			
			
			seller.addProduct(productName,productPrice,productStock);
			System.out.println("@Produk berhasil ditambahkan ke toko...");				
			TimeUnit.SECONDS.sleep(2);
			CommonUtil.clearConsole();
		} catch (NumberFormatException e) {
			CommonUtil.clearConsole();
			System.out.println("[Error] Input harus berupa angka");
		} catch (Exception e) {
			CommonUtil.clearConsole();
			System.out.println(e.getMessage());
		}
	}
	
	public static void editProduct(ProductBiz productsBiz) {
		try {
			
			Seller seller = ((Seller) Main.userCookies);
			header("Mengedit Produk Toko");
			for (Product product : seller.getProducts()) {
				System.out.println(product.toString());
			}
			footer();

			System.out.print(">>Masukkan ID Produk: ");
			String productId = CommonUtil.getUserInput();
			if(productId.isEmpty()) {
				throw new Exception("[Error] Id Produk tidak boleh kosong.");
			}
			
			Product product = seller.getProductById(productId);
			if(product == null) {
				throw new Exception("[Error] Produk dengan ID \""+productId+"\" tidak ada.");
			}
			
			System.out.println("(Nama sebelumnya: "+product.getName()+")");
			System.out.print(">>Masukkan Nama Produk: ");
			String productName = CommonUtil.getUserInput();
			if(productName.isEmpty()) {
				throw new Exception("[Error] Nama produk tidak boleh kosong.");
			}
			
			System.out.println("(Harga sebelumnya: "+CommonUtil.moneyFormat(product.getPrice())+")");
			System.out.print(">>Masukkan Harga Produk: ");
			int productPrice = Integer.parseInt(CommonUtil.getUserInput());
			if(productPrice <= 0) {
				throw new Exception("[Error] Harga produk tidak boleh kurang dari atau sama dengan 0.");
			}
			
			System.out.println("(Stok sebelumnya: "+product.getStock()+")");
			System.out.print(">>Masukkan Stok Produk: ");
			int productStock = Integer.parseInt(CommonUtil.getUserInput());
			if(productStock <= 0) {
				throw new Exception("[Error] Stock produk tidak boleh kurang dari atau sama dengan 0.");
			}			
			
			product.setName(productName);
			product.setPrice(productPrice);
			product.setStock(productStock);
			seller.editProduct(product);
			
			System.out.println("@Produk berhasil diedit...");				
			TimeUnit.SECONDS.sleep(2);
			CommonUtil.clearConsole();
		} catch (NumberFormatException e) {
			CommonUtil.clearConsole();
			System.out.println("[Error] Input harus berupa angka");
		} catch (Exception e) {
			CommonUtil.clearConsole();
			System.out.println(e.getMessage());
		}
	}
	
	public static void deleteProduct(ProductBiz pBiz) {
		try {
			Seller seller = ((Seller) Main.userCookies);
			header("Menghapus Produk Toko");
			for (Product product : seller.getProducts()) {
				System.out.println(product.toString());
			}
			footer();

			System.out.print(">>Masukkan ID Produk: ");
			String productId = CommonUtil.getUserInput();
			if(productId.isEmpty()) {
				throw new Exception("[Error] Id Produk tidak boleh kosong.");
			}
			
			Product product = seller.getProductById(productId);
			if(product == null) {
				throw new Exception("[Error] Produk dengan ID \""+productId+"\" tidak ada.");
			}
			
			seller.deleteProductById(productId);
			System.out.println("@Produk berhasil dihapus...");				
			TimeUnit.SECONDS.sleep(2);
			CommonUtil.clearConsole();
		} catch (NumberFormatException e) {
			CommonUtil.clearConsole();
			System.out.println("[Error] Input harus berupa angka");
		} catch (Exception e) {
			CommonUtil.clearConsole();
			System.out.println(e.getMessage());
		}
	}
}
