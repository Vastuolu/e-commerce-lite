package BIz;

import java.util.ArrayList;
import java.util.HashMap;

import User.User;

public class UserBiz {
	
	private ArrayList<User> users;
	
	public UserBiz() {
		
	}
	
	public HashMap<String, String> login(String inEmail, String inPass){
		HashMap<String, String> userCookies = new HashMap<String, String>();
		try {			
			User userData = null;
			for (User user : users) {
				if(user.getEmail().equals(inEmail)) {
					userData = user;
					break;
				}
			}
			
			if(userData == null) {
				throw new Exception("Email atau password Salah.");
			}else if(!userData.matchedPassword(inPass)) {
				throw new Exception("Email atau Password Salah.");
			}
			
			userCookies.put("id", userData.getId());
			userCookies.put("name", userData.getName());
			userCookies.put("email", userData.getEmail());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}			
		return userCookies;
	}
}
