package com.kjh;

import java.util.Scanner;

import com.kjh.manager.ManagerService;
import com.kjh.product.ProductService;

public class Main {

	public static final Scanner SC = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(" ----------------------------");
		System.out.println("|                            |");
		System.out.println("|          NO BRAND          |");
		System.out.println("|                            |");
		System.out.println(" ----------------------------");
		
		ManagerService ms = new ManagerService();
		ProductService ps = new ProductService();
//		ms.insertProduct();
//		ms.removeProduct();
//		ms.insertKind();
//		ms.removeKind();
		ms.insertInquiryAns();
//		ps.showProduct();
		
	}
	
}
