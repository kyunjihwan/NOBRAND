package com.kjh.manager;

import java.sql.Connection;

import com.kjh.Main;
import com.kjh.product.ProductData;
import com.kjh.product.ProductDetailShow;

public class ManagerProductInput {
	
	ProductDetailShow pds = new ProductDetailShow();
	
	public ProductData insertProduct(Connection conn) throws Exception {
		
		
		System.out.println("                ========= 상품 등록 =========");
		System.out.println();
		System.out.println("                       1. 등록상품 이름");
		System.out.print("                        입력 >> ");
		String prodName = Main.SC.nextLine();
		System.out.println("                       2. 등록상품 가격 : ");
		System.out.print("                        입력 >> ");
		int prodPrice = Main.SC.nextInt();
		Main.SC.nextLine();
		System.out.println("                       3. 등록상품 설명 : ");
		System.out.print("                        입력 >> ");
		String prodContent = Main.SC.nextLine();
		pds.showProductKind(conn);
		System.out.println("                       4. 등록상품 카테고리 선택 : ");
		System.out.print("                        입력 >> ");
		String prodKind = Main.SC.nextLine();
		pds.showProductColor(conn);
		System.out.println("                       5. 등록상품 컬러를 정하세요 : ");
		System.out.print("                        입력 >> ");
		String prodColor = Main.SC.nextLine();
		pds.showProductSize(conn);
		System.out.println("                       6. 등록상품 사이즈들을 정하세요(,표시로 나눠주세요) : ");
		System.out.print("                        입력 >> ");
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
		
		System.out.println("                ========= 상품 삭제 =========");
		pds.showProductAll(conn);
		System.out.println("                       1. 삭제상품 이름");
		System.out.print("                        입력 >> ");
		String prodName = Main.SC.nextLine();
		pds.showProductColor(conn);
		System.out.println("                       2. 삭제상품 컬러");
		System.out.print("                        입력 >> ");
		String prodColor = Main.SC.nextLine();
		
		ProductData pd = new ProductData();
		pd.setProdName(prodName);
		pd.setProdColor(prodColor);
		
		return pd;
	}
	
}
