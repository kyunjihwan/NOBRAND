package com.kjh.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductDetailShow {

	public void showProductKind(Connection conn) throws Exception {
		System.out.println("                 ───── 상품 카테고리 종류 ─────");
		String sql = "SELECT KIND_NO, KIND_NAME FROM KIND ORDER BY KIND_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String kindNum = rs.getString("KIND_NO");
			String kindName = rs.getString("KIND_NAME");
			System.out.println("                        " + kindNum + ". " + kindName);
		}
	}
	
	public void showProductColor(Connection conn) throws Exception {
		System.out.println("                 ───── 상품 색상 종류 ─────");
		String sql = "SELECT COLOR_NO, COLOR_NAME FROM COLOR ORDER BY COLOR_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String colorNum = rs.getString("COLOR_NO");
			String colorName = rs.getString("COLOR_NAME");
			System.out.println("                        " + colorNum + ". " + colorName);
		}
	}
	
	public void showProductSize(Connection conn) throws Exception {
		System.out.println("                 ───── 상품 사이즈 종류 ─────");
		String sql = "SELECT SIZE_NO, SIZE_NAME FROM MAGNITUDE ORDER BY SIZE_NO";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			String colorNum = rs.getString("SIZE_NO");
			String colorName = rs.getString("SIZE_NAME");
			System.out.println("                        " + colorNum + ". " + colorName);
		}
	}
	
	public void showProductAll(Connection conn) throws Exception {
		System.out.println();
		System.out.println("                ────────── 상품 목룍 ──────────");
		String sql = "SELECT DISTINCT COLOR_NAME, KIND_NAME, PROD_NAME, PRICE FROM PRODUCT P JOIN MAGNITUDE M ON P.SIZE_NO = M.SIZE_NO JOIN COLOR C ON P.COLOR_NO = C.COLOR_NO JOIN KIND K ON P.KIND_NO = K.KIND_NO WHERE PROD_DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String dataName = rs.getString("PROD_NAME");
			String dataColor = rs.getString("COLOR_NAME");
			String dataKind = rs.getString("KIND_NAME");
			int dataPrice = rs.getInt("PRICE");
			
			System.out.println("       상품명 : " + dataName + " | 가격 : " + dataPrice + " | 카테고리 : " + dataKind + " | 색상 : " + dataColor);
		}
		
	}
	
}
