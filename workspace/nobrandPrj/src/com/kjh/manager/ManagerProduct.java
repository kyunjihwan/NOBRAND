package com.kjh.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;
import com.kjh.product.ProductData;

public class ManagerProduct {

	ManagerProductInput mpi = new ManagerProductInput();
	
	public void insertProduct() throws Exception {
		// 커넥션 연결
		Connection conn = JdbcTemplate.getConnection();
		
		ProductData data = mpi.insertProduct(conn);
		
		String[] arrSize = data.getProdsize().split(",");
		
		int result = 0;
		
		for(int i = 0; i < arrSize.length; i++) {
			String sql = "INSERT INTO PRODUCT(PROD_NO, COLOR_NO, SIZE_NO, KIND_NO, PROD_NAME, PRICE, SIGNUP_DATE, PROD_CONTENT) VALUES (SEQ_PROD_NO.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getProdColor());
			pstmt.setString(2, arrSize[i]);
			pstmt.setString(3, data.getProdKind());
			pstmt.setString(4, data.getProdName());
			pstmt.setInt(5, data.getProdPrice());
			pstmt.setString(6, data.getProdContent());
			result = pstmt.executeUpdate();
		}
		
		if(result == 1) {
			System.out.println("상품이 등록됐습니다.");
		}else {
			System.out.println("상품 등록 실패...");
		}

		// 커넥션 연결 닫기
		conn.close();
	}
	
	
	
	public void removeProduct() throws Exception {
		
		// 커넥션 연결
		Connection conn = JdbcTemplate.getConnection();
		
		ProductData data = mpi.removeProduct(conn);
		
		String sql = "UPDATE PRODUCT SET PROD_DEL_YN = 'Y' WHERE PROD_NAME = ? AND COLOR_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, data.getProdName());
		pstmt.setInt(2, Integer.parseInt(data.getProdColor()));
		int result = pstmt.executeUpdate();
		
		if(result >= 1) {
			System.out.println("상품이 삭제됐습니다.");
		}else {
			System.out.println("상품이 삭제가 실패했습니다");
		}
		
		// 커넥션 끊기
		conn.close();
		
		
	}
	
	
	
	
	
	
	
	
}
