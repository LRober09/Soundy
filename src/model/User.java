package model;

public class User {
	private int id;
	private String username;
	private String token;

	public User(String username) {
		this.username = username;
	}
	
	public boolean authenticateUser(String password) {
		return true;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getToken() {
		return this.token;
	}
}
