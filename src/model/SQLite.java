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

	private static final String USERS = "Users";
	private static final String USERNAME = "Username";
	private static final String TOKEN = "Token";
	private static final String PASSWORD = "Password";
	private static final String ID = "ID";

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
	 * Creates a PreparedStatement to perform an Update operation. The resulting SQL
	 * query will be: "UPDATE table SET variable=variableValue WHERE
	 * condition=conditionValue"
	 * 
	 * @param connection
	 *            The database connection
	 * @param table
	 *            The table to update
	 * @param variable
	 *            The variable in the table to update
	 * @param variableValue
	 *            The new value of the specified variable
	 * @param condition
	 *            The condition by which to find the entry to update
	 * @param conditionValue
	 *            The value of the condition
	 * @return A PreparedStatement for the given Connection
	 * @throws SQLException
	 */
	private static PreparedStatement createPreparedUpdateStatement(Connection connection, String table, String variable,
			String variableValue, String condition, String conditionValue) throws SQLException {
		String base = "UPDATE ~ SET ~=? WHERE ~=?".replaceFirst("~", table).replaceFirst("~", variable)
				.replaceFirst("~", condition);
		PreparedStatement statement = connection.prepareStatement(base);
		statement.setString(1, variableValue);
		statement.setString(2, conditionValue);

		return statement;

	}

	/**
	 * Creates a PreparedStatement to perform a select operation. The resulting SQL
	 * query will be: "SELECT * FROM table WHERE condition=conditionValue"
	 * 
	 * @param connection
	 *            The database connection
	 * @param table
	 *            The table to select from
	 * @param condition
	 *            The condition by which to find the entry to select
	 * @param conditionValue
	 *            THe value of the condition
	 * @return A PreparedStatement for the given Connection
	 * @throws SQLException
	 */
	private static PreparedStatement createPreparedSelectStatement(Connection connection, String table,
			String condition, String conditionValue) throws SQLException {
		String base = "SELECT * FROM ~ WHERE ~=?".replaceFirst("~", table).replaceFirst("~", condition);
		PreparedStatement statement = connection.prepareStatement(base);
		statement.setString(1, conditionValue);
		return statement;

	}

	/**
	 * Creates a PreparedStatement to perform an insert on the User table. The
	 * resulting SQL query will be: "INSERT INTO Users (Username, Password) VALUES
	 * (username, hash)"
	 * 
	 * @param connection
	 *            The database connnection
	 * @param username
	 *            The username to insert
	 * @param hashHash
	 *            The hashed hash to insert
	 * @return A PreparedStatement for the given Connection
	 * @throws SQLException
	 */
	private static PreparedStatement createPreparedUserInsertStatement(Connection connection, String username,
			String hashHash) throws SQLException {
		String base = "INSERT INTO Users (Username, Password) VALUES (?, ?)";
		PreparedStatement statement = connection.prepareStatement(base);
		statement.setString(1, username);
		statement.setString(2, hashHash);

		return statement;
	}

	/**
	 * Insert a user into the SQLite database
	 * 
	 * @param username
	 *            User's useranme
	 * @param hashHash
	 *            User's hashed hash
	 * @return An SQLResponseCode corresponding to the result of the insert
	 *         operation
	 * @throws SQLException
	 *             if an exception occurs while trying to close the connection in a
	 *             catch block
	 */
	public static SQLResponseCodes insertUser(String username, String hashHash) throws SQLException {
		try (Connection connection = SQLite.createConnection();
				PreparedStatement statement = SQLite.createPreparedUserInsertStatement(connection, username,
						hashHash)) {
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
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage());
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
		try (Connection connection = SQLite.createConnection();
				PreparedStatement statement = SQLite.createPreparedSelectStatement(connection, USERS, USERNAME,
						username);
				ResultSet result = statement.executeQuery()) {

			int id = -1;

			while (result.next()) {
				id = result.getInt(ID);
			}

			return id;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return -1;
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
		try (Connection connection = SQLite.createConnection();
				PreparedStatement statement = SQLite.createPreparedSelectStatement(connection, USERS, USERNAME,
						username);
				ResultSet result = statement.executeQuery()) {

			String hash = "";
			while (result.next()) {
				hash = result.getString(PASSWORD);
			}

			return hash;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
			return "";
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
		try (Connection connection = SQLite.createConnection();
				PreparedStatement statement = SQLite.createPreparedSelectStatement(connection, USERS, USERNAME,
						username);
				ResultSet result = statement.executeQuery()) {

			String hash = "";
			while (result.next()) {
				hash = result.getString(TOKEN);
			}

			return hash;
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
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
	 * @throws SQLException
	 */
	public static SQLResponseCodes updateUserToken(String username, String token) throws SQLException {
		try (Connection connection = SQLite.createConnection();
				PreparedStatement statement = SQLite.createPreparedUpdateStatement(connection, USERS, TOKEN, token,
						USERNAME, username)) {
			statement.execute();
			return SQLResponseCodes.SUCCESS;
		} catch (SQLException e) {
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
		try (Connection connection = SQLite.createConnection();
				PreparedStatement statement = SQLite.createPreparedUpdateStatement(connection, USERS, TOKEN, "null",
						USERNAME, username)) {
			statement.execute();

			return SQLResponseCodes.SUCCESS;
		} catch (SQLException e) {
			return SQLResponseCodes.SQL_EXCEPTION;
		}
	}

}
