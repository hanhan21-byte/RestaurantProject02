package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static final String URL = "jdbc:mysql://localhost:3306/rm_project?useSSL=false&serverTimezone=UTC";
	private static final String USERNAME = "root"; // Thay thế bằng username của bạn
	private static final String PASSWORD = ""; // Thay thế bằng password của bạn

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		// Tải driver
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}