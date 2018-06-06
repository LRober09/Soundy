package model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User class - stores identification, authentication, and profile information
 * for a user
 * 
 * @author Lucas Robertson
 *
 */
public class User {
	private static User currentUser;
	private static final Logger logger = Logger.getLogger(User.class.getName());

	private int id;
	private String username;
	private int score;
	private String token;

	/**
	 * Constructor
	 * 
	 * @param username
	 *            The user's username
	 */
	public User(String username) {
		this.username = username;
	}

	/**
	 * Attempt to authenticate this user
	 * 
	 * @param password
	 *            The user's password in plaintext
	 * @return True if the user was successfully authenticated
	 */
	public boolean authenticate(String password) {
		String result = Authentication.authenticate(this.username, password);
		if (result.equals("invalid") || result.equals("")) {
			return false;
		} else {
			try {
				this.token = result;
				this.id = SQLite.getUserId(username);
				User.setCurrentUser(this);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	/**
	 * Determine if this user is currently authenticated
	 * 
	 * @return True if the user is authenticated
	 */
	public boolean isAuthenticated() {
		try {
			return this.token.equals(SQLite.getUserToken(this.username));
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
			return false;
		}
	}

	/**
	 * Get this user's ID
	 * 
	 * @return The user's ID
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Get this user's username
	 * 
	 * @return The user's username
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Get this user's authentication token
	 * 
	 * @return The user's auth token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * Set the global current user. This should only happen after successful
	 * authentication.
	 * 
	 * @param user
	 *            The new global current user
	 */
	public static boolean setCurrentUser(User user) {
		User.currentUser = user;
		return true;
	}

	/**
	 * Get the current global user
	 * 
	 * @return A User object, or null if the user has not been set
	 */
	public static User getCurrentUser() {
		return User.currentUser;
	}

	public void decrementScore(int i) {
		setScore(getScore() - (getScore() - i < 0 ? getScore() : 1));
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
