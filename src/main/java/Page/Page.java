package Page;

import Util.CommonUtil;
//tess
public class Page {
	private static void header() {
		System.out.println("=================================");
	}
	
	private static void header(String pageName) {
		System.out.println("=================================");
		System.out.println("-+<"+pageName.toUpperCase()+">+-");
		System.out.println("=================================");
	}

	private static void footer() {
		System.out.println("=================================");		
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
		footer();
	}
}
