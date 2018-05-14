package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import util.SQLResponseCodes;

/**
 * Class for managing user authentication.
 * 
 * @author Lucas Robertson
 *
 */
public class Authentication {

	private static final char[] CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
			.toCharArray();

	/**
	 * Attempt to authenticate a given username and password combination
	 * 
	 * @param username
	 *            The requesting user's username
	 * @param password
	 *            The requesting user's plaintext password
	 * @return An authentication token if authentication succeeded, "invalid" if the
	 *         authentication failed because of a password mismatch, or null if an
	 *         error occurred.
	 */
	public static String authenticate(String username, String password) {
		if (Authentication.hashPassword(password).equals(SQLite.getUserPassword(username))) {
			String token = Authentication.generateToken();
			if (SQLite.updateUserToken(username, token) == SQLResponseCodes.SUCCESS) {
				return token;
			} else {
				return null;
			}
		} else {
			return "invalid";
		}
	}

	/**
	 * Attempts to register a user in the database
	 * 
	 * @param username
	 *            The user's username
	 * @param password
	 *            The user's plaintext password
	 * @return An SQLResponseCode corresponding to the result of the operation
	 */
	public static SQLResponseCodes registerUser(String username, String password) {
		return SQLite.insertUser(username, Authentication.hashPassword(password));
	}

	/**
	 * Hash the provided plaintext password using SHA-256
	 * 
	 * @param plaintext
	 *            The user's plaintext password
	 * @return A hex-encoded string of the SHA-256 hash of the plaintext password
	 */
	private static String hashPassword(String plaintext) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(plaintext.getBytes(StandardCharsets.UTF_8));

			StringBuffer hashString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1) {
					hashString.append('0');
				}

				hashString.append(hex);
			}

			return hashString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Generate an 32 character random string for use as an authentication token
	 * 
	 * @return A random, alphanumeric string of length 32
	 */
	private static String generateToken() {
		Random r = new Random();
		StringBuilder token = new StringBuilder();

		for (int i = 0; i < 32; i++) {
			token.append(CHARSET[r.nextInt(CHARSET.length)]);
		}
		return token.toString();
	}

}
