package Page;

import java.util.concurrent.TimeUnit;

import Cart.Cart;
import Main.Main;
import Product.Product;
import Product.ProductBiz;
import User.Customer;
import User.UserBiz;
import Util.CommonUtil;
//tess
public class Page {
	protected static void header() {
		System.out.println("=================================");
	}
	
	protected static void header(String pageName) {
		System.out.println("=================================");
		System.out.println("-+<"+pageName.toUpperCase()+">+-");
		System.out.println("=================================");
	}

	protected static void footer() {
		System.out.println("=================================");		
	}

	protected static void footerChoice() {
		System.out.println("=================================");	
		System.out.print(">>Masukkan Pilihan: ");
	}

	public static void splashScreen(){
		header();
		System.out.println("Selamat Datang di E-Commerce Lite");
		footer();
		System.out.println("Tekan Enter Untuk Melanjutkan....");
		CommonUtil.getUserInput();
		try {			
			CommonUtil.clearConsole();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void loginRegisterMenu() {
		header("E-Commerce Lite");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("0. Keluar dari program");
		footerChoice();
	}
	

	

}
