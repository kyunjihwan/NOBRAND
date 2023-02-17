package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTemplate {

	// 커넥션 얻기
	public static Connection getConnection() throws Exception {
		// 연결 정보
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "C##KH";
		String pwd = "KH";

		// 드라이버 준비
		Class.forName(driver);

		// 커넥션 얻기
		return DriverManager.getConnection(url, id, pwd);
	}

}
