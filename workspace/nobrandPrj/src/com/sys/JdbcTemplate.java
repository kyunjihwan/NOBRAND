package com.sys;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTemplate {
	
	//커넥션 객체 리턴
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "C##KH";
		String password = "KH";
		Connection conn = DriverManager.getConnection(url,username,password);
		return conn;
	}

}//class

















