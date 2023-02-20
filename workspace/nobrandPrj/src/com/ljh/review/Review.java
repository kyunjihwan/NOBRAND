package com.ljh.review;

import java.util.Scanner;

public class Review {
	
	public static void main(String[] args) throws Exception {
		
		ReviewService rvs = new ReviewService();
		while(true) {
			boolean isFinish = rvs.startService();
			if(isFinish) {break;}
		}
	}

}
