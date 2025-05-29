package Page;

import Util.MissionUtil;
//tess
public class Page {
	private static void header() {
		System.out.println("=================================");
		System.out.println("+++++++++++++++++++++++++++++++++");		
	}
	
	private static void header(String pageName) {
		System.out.println("=================================");
		System.out.println("-+<"+pageName.toUpperCase()+">+-");
		System.out.println("+++++++++++++++++++++++++++++++++");
	}

	private static void footer() {
		System.out.println("+++++++++++++++++++++++++++++++++");
		System.out.println("=================================");		
	}
	
	public static void splashScreen() throws Exception{
		header();
		System.out.println("Selamat Datang di E-Commerce Lite");
		System.out.println("Tekan Enter Untuk Melanjutkan....");
		footer();
		MissionUtil.getUserInput();
		try {			
			MissionUtil.clearConsole();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void loginRegisterMenu() {
		header();
		System.out.println("1. Login");
		System.out.println("2. Register");
		footer();
	}
}
