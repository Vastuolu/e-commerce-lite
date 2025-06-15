package User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import Util.CommonUtil;

public class UserBiz implements IUserBiz{
	
	private HashMap<String, String> tempCookiesHash = new HashMap<String, String>();
	private HashMap<String, User> users;
		
	public UserBiz() {
		users = new HashMap<String,User>();
		
		Seller adam = new Seller("SELL-0001", "Adam", "adam@gmail.com", "12345678", "Toko AdamAlik", "Jln. Wicaksana No.22");
		adam.addProduct("Celana Jeans", 100000, 100);
		adam.addProduct("Baju Koko", 120000, 50);
        users.put("SELL-0001",adam);

        Customer anthon = new Customer("CUST-001","Anthon","anthon@gmail.com","12345678","Jln.Wicaksana No.12","+62812345678910");
        anthon.getCart().addItem(adam.getProducts().getFirst(), 2);
        anthon.getCart().addItem(adam.getProducts().getLast(), 5);
        users.put("CUST-001",anthon);
	}
	
	public void login() throws Exception{
		try {			
			//INPUT EMAIL
			System.out.print(">>Masukkan Email: ");
			String inEmail = CommonUtil.getUserInput();  
			if(!inEmail.matches(EMAIL_REGEX)) {				
				throw new Exception("[Error] Format Email Salah.");
			}
			
			//INPUT PASSWORD
			System.out.print(">>Masukkan Password: ");
			String inPassword = CommonUtil.getUserInput();
			if(inPassword.isEmpty() || inPassword.length() < 6) {				
				throw new Exception("[Error] Password tidak boleh kosong atau kurang dari 6 huruf.");
			}
			
			User userData = null;
			for (String id : users.keySet()) {
				if(users.get(id).getEmail().equals(inEmail)) {
					userData = users.get(id);
					break;
				}
			}
			
			if(userData == null) {
				throw new Exception("[Error] Email atau password Salah.");
			}else if(!userData.matchedPassword(inPassword)) {
				throw new Exception("[Error] Email atau Password Salah.");
			}
			
			Main.Main.userCookies = userData;
			System.out.println("@Login Berhasil. Selamat Datang "+userData.getName()+"...");
			TimeUnit.SECONDS.sleep(3);
			CommonUtil.clearConsole();
		} catch (Exception e) {
			throw e;
		}			
	}
	
	public void register() throws Exception{
		try {
			//INPUT NAMA
			System.out.print(">>Masukkan Nama: ");
			String name = CommonUtil.getUserInput();
			if(name.isEmpty() || name.length() < 3) {
				throw new Exception("[Error] Nama tidak boleh kosong atau kurang dari 3 huruf.");
			}
			
			//INPUT EMAIL
			System.out.print(">>Masukkan Email: ");
			String email = CommonUtil.getUserInput();  
			if(!email.matches(EMAIL_REGEX)) {				
				throw new Exception("[Error] Format Email Salah.");
			}
	
			//INPUT PASSWORD
			System.out.print(">>Masukkan Password: ");
			String password = CommonUtil.getUserInput();
			if(password.isEmpty() || password.length() < 6) {				
				throw new Exception("[Error] Password tidak boleh kosong atau kurang dari 6 huruf.");
			}
			
			//INPUT USER TYPE
			System.out.println(">1. Customer");
			System.out.println(">2. Seller");
			System.out.print(">>Pilih Jenis User(1/2): ");
			int typeChoose = Integer.parseInt(CommonUtil.getUserInput());
			if(typeChoose != 1 && typeChoose != 2) {				
				throw new Exception("[Error] Pilihan User Type antara 1 atau 2.");
			}

			String telpOrTok = null;
			if(typeChoose == 1 ) {
				System.out.print(">>Masukkan Nomor Telepon: ");
				telpOrTok = CommonUtil.getUserInput();				
			}else {
				System.out.print(">>Masukkan Nama Toko: ");
				telpOrTok = CommonUtil.getUserInput();								
			}
			if(telpOrTok.isEmpty()) {				
				throw new Exception("[Error] Data tidak boleh kosong");
			}
			
			//INPUT ADDRESS
			String outAddress = typeChoose == 1 ? ">>Alamat Rumah: " : ">>Alamat Toko: ";
			System.out.print(outAddress);
			String address = CommonUtil.getUserInput();
			if(address.isEmpty()) {				
				throw new Exception("[Error] Alamat tidak boleh kosong");
			}
			
			String userId = typeChoose == 1 ? "CUST" : "SELL" + "-"+users.size()+1;
			User userData;
			if(typeChoose == 1) {
				userData = new Customer(userId,name,email,password,address,telpOrTok);
			}else {
				userData = new Seller(userId,name,email,password,telpOrTok,address);				
			}
			users.put(userId,userData);
			
			Main.Main.userCookies = userData;
			System.out.println("@Register Berhasil. Selamat Datang "+name+"...");
			TimeUnit.SECONDS.sleep(3);
			CommonUtil.clearConsole();
		}catch(NumberFormatException e) {
			throw e;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void logout() {
		Main.Main.userCookies = null;
		System.out.println("@Logout Berhasil. Selamat Tinggal...");
	}
	
	public User getUserById(String userId) {
		return users.get(userId);
	}
	
	public HashMap<String,User> getAllUser(){
		return users;
	}
}
