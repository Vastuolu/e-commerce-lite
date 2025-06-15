package Main;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import Page.CustomerPage;
import Page.Page;
import Product.ProductBiz;
import User.Customer;
import User.User;
import User.UserBiz;
import Util.CommonUtil;

public class Main {
	public static User userCookies = null;
	public static void main(String[] args) {
		UserBiz uBiz = new UserBiz();
		ProductBiz pBiz = new ProductBiz();
		int pilihan = -1;

		//STARTING POINT OF PAGE
		CommonUtil.clearConsole();
		Page.splashScreen();

		//LOGIN OR REGISTER PAGE
		while(pilihan != 0 ) {				
			try {
				Page.loginRegisterMenu();
				pilihan = Integer.parseInt(CommonUtil.getUserInput());
				
				if(pilihan == 0 ) {
					CommonUtil.clearConsole();
					System.out.println("@Keluar dari program. Selamat Tinggal.....");
					TimeUnit.SECONDS.sleep(2);
					return;
				}else if(pilihan != 1 && pilihan != 2) {
					System.out.println("[Error] Pilihan Tidak ada. Silahkan Masukkan angka yang benar");
					CommonUtil.clearConsole();
					continue;
				}
				
				if(pilihan == 1) {
					uBiz.login();
				}else if(pilihan == 2) {
					uBiz.register();
				}
				
				if(userCookies instanceof Customer) {
					customerPOV(uBiz,pBiz);
				}else {
					sellerPOV(uBiz,pBiz);
				}
				TimeUnit.SECONDS.sleep(2);
				CommonUtil.clearConsole();
			} catch (Exception e) {
				CommonUtil.clearConsole();
				System.out.println(e.getMessage());
				pilihan = -1;
				continue;
			}	
		}	
		
	}
	
	private static void customerPOV(UserBiz uBiz, ProductBiz pBiz) {
		int pilihan = -1;
		while(pilihan != 9) {
			try {
				CustomerPage.berandaMenu();
				pilihan = Integer.parseInt(CommonUtil.getUserInput());
				CommonUtil.clearConsole();

				switch (pilihan) {
					case 1:
						CustomerPage.listProductMenu(pBiz, uBiz);
						break;
					case 2:
						CustomerPage.cartMenu();
						break;
					case 3:
						
						break;
					case 9:
						uBiz.logout();
						return;
					default:
						throw new Exception("[Error] Pilihan Tidak ada. Silahkan Masukkan angka yang benar");
					}
				
				CommonUtil.clearConsole();
			} catch (Exception e) {
				CommonUtil.clearConsole();
				System.out.println(e.getMessage());
				pilihan = 0;
				continue;
			}
		}
	}

	private static void sellerPOV(UserBiz uBiz, ProductBiz pBiz) {
		int pilihan = -1;
		while(pilihan != 9) {
			try {
//				SellerPage.berandaMenu();
				pilihan = Integer.parseInt(CommonUtil.getUserInput());
				CommonUtil.clearConsole();
				switch (pilihan) {
				case 1:
					
					break;
				case 9:
					
					break;

				default:
					break;
				}
			} catch (Exception e) {
				CommonUtil.clearConsole();
				System.out.println(e.getMessage());
				pilihan = 0;
				continue;
			}
		}
	}
}
