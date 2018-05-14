package model;

public class Authentication {
	public static String authenticate(String username, String password) {
		return Authentication.hashPassword(password);
	}

	public static String registerUser(String username, String password) {
		
		return password;
	}

	private static String hashPassword(String plaintext) {
		return plaintext;
	}
	
	
	
}
