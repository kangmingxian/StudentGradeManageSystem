package com.wenr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String driver = "com.mysql.jdbc.Driver";
	/* jz */
	private static final String url = "jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=UTF-8";
	/* end */
	private static final String username = "root";
	private static final String password = "kzn1597484245";

	private static Connection conn;

	static {
		try {
			Class.forName(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		if (conn == null) {
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		}
		return conn;
	}
}
