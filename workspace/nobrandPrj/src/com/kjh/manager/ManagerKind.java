package com.kjh.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;
import com.kjh.product.ProductDetailShow;

public class ManagerKind {
	
	ManagerKindInput mki = new ManagerKindInput();

	// 카테고리 등록
	public void insertKind() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();

		String kindName = mki.insertKind();
		
		String sql = "INSERT INTO KIND(KIND_NO, KIND_NAME, KIND_ENROLLDATE) VALUES (SEQ_KIND_NO.NEXTVAL, ?, SYSDATE)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, kindName);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("카테고리 등록을 성곻했습니다.");
		}else {
			System.out.println("카테고리 등록 실패");
		}
		
		conn.close();
		
		
	}
	
	public void removeKind() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();

		String kindName = mki.removeKind(conn);
		
		String sql = "DELETE FROM KIND WHERE KIND_NAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, kindName);
		int result = pstmt.executeUpdate();
		
		if(result == 1) {
			System.out.println("카테고리 삭제 성공");
		}else {
			System.out.println("카테고리 삭제 실패");
		}
		
		conn.close();
		
	}
	
	
	
	
	
	
	
} // class
