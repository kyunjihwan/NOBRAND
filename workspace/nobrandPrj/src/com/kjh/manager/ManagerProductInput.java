package com.kjh.manager;

import java.sql.Connection;

import com.kjh.Main;
import com.kjh.product.ProductData;
import com.kjh.product.ProductDetailShow;

public class ManagerProductInput {
	
	ProductDetailShow pds = new ProductDetailShow();
	
	public ProductData insertProduct(Connection conn) throws Exception {
		
		
		System.out.println("===== 상품 등록 ======");
		System.out.print("등록상품 이름 : ");
		String prodName = Main.SC.nextLine();
		System.out.print("등록상품 가격 : ");
		int prodPrice = Main.SC.nextInt();
		Main.SC.nextLine();
		System.out.print("등록상품 설명 : ");
		String prodContent = Main.SC.nextLine();
		pds.showProductKind(conn);
		System.out.print("등록상품 카테고리 선택 : ");
		String prodKind = Main.SC.nextLine();
		pds.showProductColor(conn);
		System.out.print("등록상품 컬러를 정하세요 : ");
		String prodColor = Main.SC.nextLine();
		pds.showProductSize(conn);
		System.out.print("등록상품 사이즈들을 정하세요(,표시로 나눠주세요) : ");
		String prodSize = Main.SC.nextLine();
		
		ProductData pd = new ProductData();
		pd.setProdName(prodName);
		pd.setProdColor(prodColor);
		pd.setProdContent(prodContent);
		pd.setProdKind(prodKind);
		pd.setProdPrice(prodPrice);
		pd.setProdsize(prodSize);
		
		return pd;
		
	}
	
	public ProductData removeProduct(Connection conn) throws Exception {
		
		System.out.println("===== 상품 삭제 =====");
		pds.showProductAll(conn);
		System.out.print("삭제 상품 이름 : ");
		String prodName = Main.SC.nextLine();
		pds.showProductColor(conn);
		System.out.print("삭제 상품 컬러 : ");
		String prodColor = Main.SC.nextLine();
		
		ProductData pd = new ProductData();
		pd.setProdName(prodName);
		pd.setProdColor(prodColor);
		
		return pd;
	}
	
	
}
