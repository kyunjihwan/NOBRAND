package com.kjh.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jdbc.JdbcTemplate;
import com.kjh.Main;
import com.kjh.manager.ManagerProduct;

public class ProductShow {	
	
	public void showProduct() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("===== 카테고리 =====");
		new ProductDetailShow().showProductKind(conn);
		System.out.print("카테고리를 선택하세요 : ");
		String kindNum = Main.SC.nextLine();
		
		showProductitem(kindNum, conn);
		System.out.print("상품명 선택 : ");
		String prodNum = Main.SC.nextLine();
		
		String sql = "SELECT COLOR_NAME, SIZE_NAME, PROD_NAME, PRICE, PROD_CONTENT FROM PRODUCT P JOIN MAGNITUDE M ON P.SIZE_NO = M.SIZE_NO JOIN COLOR C ON P.COLOR_NO = C.COLOR_NO WHERE PROD_NAME = ? AND KIND_NO = ? AND PROD_DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, prodNum);
		pstmt.setInt(2, Integer.parseInt(kindNum));
		System.out.println(prodNum + " " + kindNum);
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<String> sizeArr = new ArrayList();
		String prodName = "";
		String prodColor = "";
		String prodContent = "";
		int prodPrice = 0;
		
		while(rs.next()) {
			prodName = rs.getString("PROD_NAME");
			prodColor = rs.getString("COLOR_NAME");
			prodContent = rs.getString("PROD_CONTENT");
			prodPrice = rs.getInt("PRICE");
			sizeArr.add(rs.getString("SIZE_NAME"));
		}
		
		System.out.println("===== " + prodName + " =====");
		System.out.println("상품 설명 : " + prodContent);
		System.out.println("가격 : " + prodPrice);
		System.out.print("사이즈 : ");
		for(int i = 0; i < sizeArr.size(); i++) {
			System.out.print(sizeArr.get(i) + "| ");
		}
		System.out.println();
		
		System.out.println("1. 구매");
		System.out.println("2. 돌아가기");
		int choiceNum = Main.SC.nextInt();
		
//		switch(choiceNum) {
//		
//		}
		
		conn.close();
		
	}
	
	public void showProductitem(String kindNum, Connection conn) throws Exception {
		String sql = "SELECT DISTINCT PROD_NAME, COLOR_NAME, PRICE FROM PRODUCT D JOIN COLOR C ON D.COLOR_NO = C.COLOR_NO WHERE KIND_NO = ? AND PROD_DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(kindNum));
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String prodName = rs.getString("PROD_NAME");
			String prodColor = rs.getString("COLOR_NAME");
			String prodPrice = rs.getString("PRICE");
			
			System.out.println("상품명 : " + prodName + " 색상 : " + prodColor + " 가격 : " + prodPrice);
		}
	}
	
}
