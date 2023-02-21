package com.kjh.manager;

import java.sql.Connection;

import com.kjh.Main;
import com.kjh.product.ProductDetailShow;

public class ManagerKindInput {

	// 카테고리 등록 정보 입력
	public String insertKind() {
		System.out.println("===== 카테고리 등록 =====");
		System.out.println("새로운 카테고리 등록 이름(2글자) : ");
		String kindName = Main.SC.nextLine();
		
		return kindName;
	}
	
	// 카테고리 삭제 정보 입력
	public String removeKind(Connection conn) throws Exception {
		System.out.println("===== 카테고리 삭제 =====");
		new ProductDetailShow().showProductKind(conn);
		System.out.print("삭제할 카테고리 이름 : ");
		String kindName = Main.SC.nextLine();
		
		return kindName;
	}
	
}
