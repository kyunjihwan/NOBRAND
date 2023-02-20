package com.sys;

import java.util.Scanner;

public class Main {
	
	public static final Scanner sc = new Scanner(System.in);
	public static String loginMemberNick;
	public static String loginManagerNick;
	public static String loginMemberNo;
	
	
	public static void main(String[] args) throws Exception {
		
		MainService ms = new MainService();
		System.out.println("==========");

		//비니지스 로직
		while(true) {
			boolean isFinish = ms.startService();
			if(isFinish) {break;}
		}
		
		System.out.println("=====프로그램 종료=====");
		
	}//main

}//class
























