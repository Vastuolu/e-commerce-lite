package Biz;

import java.util.ArrayList;
import java.util.HashMap;

import User.*;
import Util.MissionUtil;

public class UserBiz {
	
	private HashMap<String, String> tempCookiesHash = new HashMap<String, String>();
	private ArrayList<User> users;
	private String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	public UserBiz() {
		users.add(new Customer("User-001","Anthon","anthon@gmail.com","12345678","Jln.Wicaksana No.12","+62812345678910"));
		users.add(new Seller("Seller-001","Adam","adam@gmail.com","12345678","Toko AdamAlik","Jln.Wicaksana No.22"));
	}
	
	public void login() throws Exception{
		try {			
			//INPUT EMAIL
			System.out.print(">>Masukkan Email: ");
			String inEmail = MissionUtil.getUserInput();  
			if(!inEmail.matches(emailRegex)) {				
				throw new Exception("Format Email Salah.");
			}
			
			//INPUT PASSWORD
			System.out.print(">>Masukkan Password: ");
			String inPassword = MissionUtil.getUserInput();
			if(inPassword.isEmpty() || inPassword.length() < 6) {				
				throw new Exception("Password tidak boleh kosong atau kurang dari 6 huruf.");
			}
			
			User userData = null;
			for (User user : users) {
				if(user.getEmail().equals(inEmail)) {
					userData = user;
					break;
				}
			}
			
			if(userData == null) {
				throw new Exception("Email atau password Salah.");
			}else if(!userData.matchedPassword(inPassword)) {
				throw new Exception("Email atau Password Salah.");
			}
			
			tempCookiesHash.put("id", userData.getId());
			tempCookiesHash.put("name", userData.getName());
			tempCookiesHash.put("email", userData.getEmail());
			tempCookiesHash.put("userType", userData instanceof Customer ? "Customer" : "Seller");
			Main.Main.userCookies = tempCookiesHash;
		} catch (Exception e) {
			throw e;
		}			
	}
	
	public void register() throws Exception{
		try {
			//INPUT NAMA
			System.out.print(">>Masukkan Name: ");
			String name = MissionUtil.getUserInput();
			if(name.isEmpty() || name.length() < 3) {
				throw new Exception("Nama tidak boleh kosong atau kurang dari 3 huruf.");
			}
			
			//INPUT EMAIL
			System.out.print(">>Masukkan Email: ");
			String email = MissionUtil.getUserInput();  
			if(!email.matches(emailRegex)) {				
				throw new Exception("Format Email Salah.");
			}
	
			//INPUT PASSWORD
			System.out.print(">>Masukkan Password: ");
			String password = MissionUtil.getUserInput();
			if(password.isEmpty() || password.length() < 6) {				
				throw new Exception("Password tidak boleh kosong atau kurang dari 6 huruf.");
			}
			
			//INPUT USER TYPE
			System.out.println(">1. Customer");
			System.out.println(">2. Seller");
			System.out.print(">>Pilih Jenis User(1/2): ");
			int typeChoose = Integer.parseInt(MissionUtil.getUserInput());
			if(typeChoose != 1 || typeChoose != 2) {				
				throw new Exception("Pilihan User Type antara 1 atau 2.");
			}

			String telpOrTok = null;
			if(typeChoose == 1 ) {
				System.out.print(">>Masukkan Nomor Telepon: ");
				telpOrTok = MissionUtil.getUserInput();				
			}else {
				System.out.print(">>Masukkan Nama Toko: ");
				telpOrTok = MissionUtil.getUserInput();								
			}
			
			//INPUT ADDRESS
			String outAddress = typeChoose == 1 ? ">>Alamat Rumah: " : ">>Alamat Toko: ";
			System.out.println(outAddress);
			String address = MissionUtil.getUserInput();
			if(address.isEmpty() || address.length() < 6) {				
				throw new Exception("Alamat tidak boleh kosong atau kurang dari 6 huruf.");
			}
			
			String userId = "User-"+users.size();
			if(typeChoose == 1) {				
				users.add(new Customer(userId,name,email,password,address,telpOrTok));
			}else {
				users.add(new Seller(userId,name,email,password,telpOrTok,address));				
			}
			
			tempCookiesHash.put("id", userId);
			tempCookiesHash.put("name", name);
			tempCookiesHash.put("email", email);
			tempCookiesHash.put("userType", typeChoose == 1 ? "Customer" : "Seller");
			Main.Main.userCookies = tempCookiesHash;
		}catch(NumberFormatException e) {
			throw e;
		}catch(Exception e) {
			throw e;
		}
	}
}
