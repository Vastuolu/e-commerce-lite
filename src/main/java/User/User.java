package User;

import java.util.HashMap;

public abstract class User {
	private String id;
	private String name;
	private String email;
	private String password;
	
	public User(String id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public boolean matchedPassword(String pass) {
		return password.equals(pass);
	}
	
	
}
