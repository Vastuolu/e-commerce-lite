package User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import Util.CommonUtil;

public class UserBiz implements IUserBiz{
	
	private HashMap<String, String> tempCookiesHash = new HashMap<String, String>();
	private ArrayList<User> users;
	private String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	
	public UserBiz() {
		users = new ArrayList<User>();
		users.add(new Customer("User-001","Anthon","anthon@gmail.com","12345678","Jln.Wicaksana No.12","+62812345678910"));
		
        users.add(new Seller("SELLER-0001", "Adam", "adam@gmail.com", "12345678", "Toko AdamAlik", "Jln. Wicaksana No.22"));
        users.add(new Seller("SELLER-0002", "Bella", "bella@gmail.com", "12345678", "Bella Store", "Jln. Kenanga No.25"));
        users.add(new Seller("SELLER-0003", "Carlos", "carlos@gmail.com", "12345678", "Carlos Mart", "Jln. Mawar No.30"));
        users.add(new Seller("SELLER-0004", "Daisy", "daisy@gmail.com", "12345678", "Daisy Shop", "Jln. Melati No.33"));
        users.add(new Seller("SELLER-0005", "Edward", "edward@gmail.com", "12345678", "Edward Supplies", "Jln. Dahlia No.40"));
	}
	
	public void login() throws Exception{
		try {			
			//INPUT EMAIL
			System.out.print(">>Masukkan Email: ");
			String inEmail = CommonUtil.getUserInput();  
			if(!inEmail.matches(emailRegex)) {				
				throw new Exception("[Error] Format Email Salah.");
			}
			
			//INPUT PASSWORD
			System.out.print(">>Masukkan Password: ");
			String inPassword = CommonUtil.getUserInput();
			if(inPassword.isEmpty() || inPassword.length() < 6) {				
				throw new Exception("[Error] Password tidak boleh kosong atau kurang dari 6 huruf.");
			}
			
			User userData = null;
			for (User user : users) {
				if(user.getEmail().equals(inEmail)) {
					userData = user;
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
			if(!email.matches(emailRegex)) {				
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
			
			String userId = "User-"+users.size()+1;
			User userData;
			if(typeChoose == 1) {
				userData = new Customer(userId,name,email,password,address,telpOrTok);
			}else {
				userData = new Seller(userId,name,email,password,telpOrTok,address);				
			}
			users.add(userData);
			
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
		for (User user : users) {
			if(user.getId().equals(userId)) {
				return user;
			}
		}
		return null;
	}
}
