package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteException;

public class SQLite {
	private static final String DB_URL = "jdbc:sqlite:soundy.db";
	
	private static Connection createConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL);
		connection.setAutoCommit(true);
		
		return connection;
	}
	
	private static String generateToken() {
		return "";
	}
	
	public static int insertUser(String username, String password) {
		Connection connection;
		Statement statement;
		try {
			connection = SQLite.createConnection();
			
			statement = connection.createStatement();
			statement.execute("INSERT INTO Users (Username, Password) VALUES (\"" + username + "\", \"" + password + "\")");
			
			statement.close();
			connection.close();
			
			return 0;
		} catch (SQLiteException e) {
			if(e.getErrorCode() == 19) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 2;
	}
	
	public static String createUserToken(String username) {
		Connection connection;
		Statement statement;
		String token;
		try {
			connection = SQLite.createConnection();
			statement = connection.createStatement();
			statement.execute("UPDATE Users SET Token=\"" + "" + "\" WHERE Username=\"" + username + "\"");
		} catch (Exception e) {
			
		}
		
		return "";
	}
}
