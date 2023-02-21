package com.nobrand.main;

import java.util.Scanner;

import com.sys.MainService;

public class Main {
	
	public static final Scanner SC = new Scanner(System.in);
	public static String loginMemberNick;
	public static String loginManagerNick;
	public static String loginMemberNo;
	
	
	public static void main(String[] args) throws Exception {
		
		MainService ms = new MainService();
		//비니지스 로직
		while(true) {
			boolean isFinish = ms.startService();
			if(isFinish) {break;}
		}
		
		System.out.println("=====프로그램 종료=====");
		
	}//main

}//class