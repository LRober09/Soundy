package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sqlite.SQLiteException;

import util.SQLResponseCodes;

public class SQLite {
	private static final String DB_URL = "jdbc:sqlite:soundy.db";
	private static final Logger logger = Logger.getLogger(SQLite.class.getName());

	private static final String TOKEN = "Token";
	private static final String PASSWORD = "Password";
	private static final String ID = "ID";
	
	private static final String SELECT_FROM_USERS = "SELECT * FROM Users WHERE Username=?";

	private SQLite() {
	}


	public static SQLResponseCodes insertUser(String username, String hashHash) throws SQLException {
		String base = "INSERT INTO Users (Username, Password) VALUES (?, ?)";
		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(base);) {
			connection.setAutoCommit(true);
			statement.setString(1, username);
			statement.setString(2, hashHash);

			statement.execute();
			return SQLResponseCodes.SUCCESS;
		} catch (SQLiteException e) {
			if (e.getErrorCode() == 19) {
				return SQLResponseCodes.ENTRY_EXISTS;
			} else if (e.getErrorCode() == 5) {
				return SQLResponseCodes.DB_LOCKED;
			} else {
				return SQLResponseCodes.SQL_EXCEPTION;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return SQLResponseCodes.SQL_EXCEPTION;
		}
	}

	public static int getUserId(String username) throws SQLException {
		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USERS);) {
			connection.setAutoCommit(true);
			statement.setString(1, username);

			return tryGetUserId(statement);
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return -1;
		}
	}
	
	private static int tryGetUserId(PreparedStatement statement) throws SQLException {
		try (ResultSet result = statement.executeQuery();) {
			int id = -1;

			while (result.next()) {
				id = result.getInt(ID);
			}

			return id;
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	/**
	 * Retrieves a user's hashed hash from the database
	 * 
	 * @param username
	 *            The user's username
	 * @return
	 * @throws SQLException
	 */
	public static String getUserPassword(String username) throws SQLException {
		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USERS);) {
			connection.setAutoCommit(true);
			statement.setString(1, username);

			return tryGetUserPassword(statement);
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return "";
		}

	}
	
	private static String tryGetUserPassword(PreparedStatement statement) throws SQLException {
		try (ResultSet result = statement.executeQuery();) {
			String hash = "";
			while (result.next()) {
				hash = result.getString(PASSWORD);
			}

			return hash;
		} catch (Exception e) {
			throw new SQLException();
		}

	}


	/**
	 * Retrieves a user's authentication token from the database
	 * 
	 * @param username
	 *            The user's username
	 * @return The user's authentication token (string)
	 * @throws SQLException
	 */
	public static String getUserToken(String username) throws SQLException {
		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(SELECT_FROM_USERS);) {
			connection.setAutoCommit(true);
			statement.setString(1, username);

			return tryGetUserToken(statement);

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return "";
		}
	}
	
	private static String tryGetUserToken(PreparedStatement statement) throws SQLException {
		try (ResultSet result = statement.executeQuery();) {
			String hash = "";
			while (result.next()) {
				hash = result.getString(TOKEN);
			}

			return hash;
		} catch (Exception e) {
			throw new SQLException();
		}
	}

	/**
	 * Updates a user's authentication token
	 * 
	 * @param username
	 *            The user's username
	 * @param token
	 *            The user's new authentication token
	 * @return An SQLResponseCode corresponding to the result of the update
	 *         operation
	 * @throws SQLException
	 */
	public static SQLResponseCodes updateUserToken(String username, String token) throws SQLException {
		String base = "UPDATE Users SET Token=? WHERE Username=?";
		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(base);) {
			connection.setAutoCommit(true);
			statement.setString(1, token);
			statement.setString(2, username);
			
			statement.execute();
			return SQLResponseCodes.SUCCESS;
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return SQLResponseCodes.SQL_EXCEPTION;
		}
	}

	/**
	 * Clears a user's authentication token
	 * 
	 * @param username
	 *            The user's useranme
	 * @return An SQLResponseCode
	 * @throws SQLException
	 */
	public static SQLResponseCodes clearUserToken(String username) throws SQLException {
		String base = "UPDATE Users SET Token=null WHERE Username=?";
		try (Connection connection = DriverManager.getConnection(DB_URL);
				PreparedStatement statement = connection.prepareStatement(base);) {
			connection.setAutoCommit(true);
			statement.setString(1, username);
			
			statement.execute();
			return SQLResponseCodes.SUCCESS;
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return SQLResponseCodes.SQL_EXCEPTION;
		}
	}

}
