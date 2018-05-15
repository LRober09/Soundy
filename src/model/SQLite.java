package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteException;

import util.SQLResponseCodes;

public class SQLite {
	private static final String DB_URL = "jdbc:sqlite:soundy.db";

	private SQLite() {
	}

	/**
	 * Creates a connection with the database specified by DB_URL
	 * 
	 * @return An open connection to a database
	 * @throws SQLException
	 *             If an error occurs when opening a connection
	 */
	private static Connection createConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL);
		connection.setAutoCommit(true);

		return connection;
	}

	/**
	 * Insert a user into the SQLite database
	 * 
	 * @param username
	 *            User's useranme
	 * @param passwordHash
	 *            User's hashed password
	 * @return An SQLResponseCode corresponding to the result of the insert
	 *         operation
	 * @throws SQLException
	 *             if an exception occurs while trying to close the connection in a
	 *             catch block
	 */
	public static SQLResponseCodes insertUser(String username, String passwordHash) throws SQLException {
		try (Connection connection = SQLite.createConnection()) {
			Statement statement = connection.createStatement();
			statement.execute(
					"INSERT INTO Users (Username, Password) VALUES (\"" + username + "\", \"" + passwordHash + "\")");

			statement.close();
			connection.close();

			return SQLResponseCodes.SUCCESS;
		} catch (SQLiteException e) {
			if (e.getErrorCode() == 19) {
				return SQLResponseCodes.ENTRY_EXISTS;
			} else if (e.getErrorCode() == 5) {
				return SQLResponseCodes.DB_LOCKED;
			} else {
				return SQLResponseCodes.SQL_EXCEPTION;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return SQLResponseCodes.SQL_EXCEPTION;
		}

	}

	/**
	 * Retrieves a user's
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public static int getUserId(String username) throws SQLException {
		try (Connection connection = SQLite.createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(SQLite.buildUserSelectStatement(username));
			int id = result.getInt("ID");
			statement.close();
			connection.close();

			return id;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * Retrieves a user's hashed password from the database
	 * 
	 * @param username
	 *            The user's username
	 * @return
	 */
	public static String getUserPassword(String username) {
		try (Connection connection = SQLite.createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(SQLite.buildUserSelectStatement(username));
			String password = result.getString("Password");
			statement.close();
			connection.close();

			return password;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves a user's authentication token from the database
	 * 
	 * @param username
	 *            The user's username
	 * @return The user's authentication token (string)
	 */
	public static String getUserToken(String username) {
		try (Connection connection = SQLite.createConnection()) {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(SQLite.buildUserSelectStatement(username));
			String password = result.getString("Token");
			statement.close();
			connection.close();

			return password;
		} catch (Exception e) {
			return null;
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
	 */
	public static SQLResponseCodes updateUserToken(String username, String token) {
		try (Connection connection = SQLite.createConnection()) {
			Statement statement = connection.createStatement();
			statement.execute("UPDATE Users SET Token=\"" + token + "\" WHERE Username=\"" + username + "\"");
			statement.close();
			connection.close();

			return SQLResponseCodes.SUCCESS;
		} catch (SQLException e) {
			e.printStackTrace();
			return SQLResponseCodes.SQL_EXCEPTION;
		}
	}

	/**
	 * Clears a user's authentication token
	 * 
	 * @param username
	 *            The user's useranme
	 * @return An SQLResponseCode
	 */
	public static SQLResponseCodes clearUserToken(String username) {
		try (Connection connection = SQLite.createConnection()) {
			Statement statement = connection.createStatement();
			statement.execute("UPDATE Users SET Token = null WHERE Username=\"" + username + "\"");
			statement.close();
			connection.close();

			return SQLResponseCodes.SUCCESS;
		} catch (SQLException e) {
			return SQLResponseCodes.SQL_EXCEPTION;
		}
	}
	
	
	/**
	 * @param table
	 * @param username
	 * @return
	 */
	private static String buildUserSelectStatement(String username) {
		return "SELECT * FROM Users WHERE Username=\"" + username + "\"";
	}
}