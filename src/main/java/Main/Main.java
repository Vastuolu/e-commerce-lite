package Main;

import java.util.HashMap;

import Page.Page;
import User.UserBiz;
import Util.CommonUtil;

public class Main {
	public static HashMap<String, String> userCookies = null;
	public static void main(String[] args) {
		UserBiz uBiz = new UserBiz();
		int pilihan = -1;

		//STARTING POINT OF PAGE
		CommonUtil.clearConsole();
		Page.splashScreen();

		//LOGIN OR REGISTER PAGE
		while(pilihan != 1 && pilihan != 2) {				
			try {
				Page.loginRegisterMenu();
				System.out.print(">>Masukkan Pilihan: ");
				pilihan = Integer.parseInt(CommonUtil.getUserInput());
				if(pilihan == 1) {
					uBiz.login();
				}else if(pilihan == 2) {
					uBiz.register();			
				}else {
					System.out.println("[Error] Pilihan Tidak ada. Silahkan Masukkan angka yang benar");
					CommonUtil.clearConsole();
					continue;
				}
			} catch (Exception e) {
				CommonUtil.clearConsole();
				System.out.println(e.getMessage());
				pilihan = 0;
				continue;
			}	
		}	
		
		//HOMEPAGE
		
	}

}
