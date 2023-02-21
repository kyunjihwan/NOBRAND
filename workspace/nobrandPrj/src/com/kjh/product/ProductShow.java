package com.kjh.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jdbc.JdbcTemplate;
import com.ksk.basket.BasketInput;
import com.nobrand.main.Main;
import com.sys.MemberService;

public class ProductShow {	

	
	private MemberService ms = new MemberService();
	private BasketInput bi = new BasketInput();
	
	
	public void showProduct() throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		System.out.println("===== 카테고리 =====");
		new ProductDetailShow().showProductKind(conn);
		System.out.print("카테고리를 선택하세요 : ");
		String kindNum = Main.SC.nextLine();
		
		showProductitem(kindNum, conn);
		System.out.print("상품명 선택 : ");
		String prodName = Main.SC.nextLine();
		
		String sql = "SELECT COLOR_NAME, SIZE_NAME, PROD_NAME, PRICE, PROD_CONTENT FROM PRODUCT P JOIN MAGNITUDE M ON P.SIZE_NO = M.SIZE_NO JOIN COLOR C ON P.COLOR_NO = C.COLOR_NO WHERE PROD_NAME = ? AND KIND_NO = ? AND PROD_DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, prodName);
		pstmt.setInt(2, Integer.parseInt(kindNum));
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<String> sizeArr = new ArrayList();
		String dbprodName = "";
		String dbprodColor = "";
		String dbprodContent = "";
		int dbprodPrice = 0;
		
		while(rs.next()) {
			dbprodName = rs.getString("PROD_NAME");
			dbprodColor = rs.getString("COLOR_NAME");
			dbprodContent = rs.getString("PROD_CONTENT");
			dbprodPrice = rs.getInt("PRICE");
			sizeArr.add(rs.getString("SIZE_NAME"));
		}
		
		System.out.println("===== " + dbprodName + " =====");
		System.out.println("상품 설명 : " + dbprodContent);
		System.out.println("컬러 : " + dbprodColor);
		System.out.println("가격 : " + dbprodPrice);
		System.out.print("사이즈 : ");
		for(int i = 0; i < sizeArr.size(); i++) {
			System.out.print(sizeArr.get(i));
		}
		System.out.println();
		
		System.out.println("1. 구매");
		System.out.println("2. 좋아요 등록");
		System.out.println("9. 돌아가기");
		String choiceNum = Main.SC.nextLine();
		
		if(choiceNum.equals("9")) {
			return ; 
		}
		
		switch(choiceNum) {
		case "1" : bi.inputBasket(buyProduct(prodName, conn), conn); break;
		case "2" : ms.insertGreat(findProduct(prodName, conn)); break;
		}
		
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
	
	public String findProduct(String proName,Connection conn) throws Exception {
		String sql = "SELECT PROD_NO FROM PRODUCT WHERE PROD_NAME = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, proName);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		String prodNum = rs.getString("PROD_NO");
		
		return prodNum;
	}
	
	public String buyProduct(String proName, Connection conn) throws Exception {
		
		System.out.print("사이즈 선택(1. S / 2. M / 3. L / 4. XL) : ");
		int proSize = Main.SC.nextInt();
		Main.SC.nextLine();
		String sql = "SELECT PROD_NO FROM PRODUCT P JOIN MAGNITUDE M ON P.SIZE_NO = M.SIZE_NO WHERE PROD_NAME = ? AND P.SIZE_NO = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, proName);
		pstmt.setInt(2, proSize);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		String proNum = rs.getString("PROD_NO");
		
		return proNum;
	};
	
}
