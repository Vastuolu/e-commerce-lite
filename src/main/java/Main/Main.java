package Main;

import java.util.HashMap;

import Biz.UserBiz;
import Page.Page;
import Util.MissionUtil;

public class Main {
	public static HashMap<String, String> userCookies = null;
	public static void main(String[] args) {
		try {
			
			UserBiz uBiz = new UserBiz();
			int pilihan;
			Page.splashScreen();
			Page.loginRegisterMenu();
			pilihan = Integer.parseInt(MissionUtil.getUserInput());
			while(pilihan != 1 || pilihan != 2) {				
				if(pilihan == 1) {
					uBiz.login();
				}else if(pilihan == 2) {
					uBiz.register();			
				}else {
					continue;
				}
			}
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
