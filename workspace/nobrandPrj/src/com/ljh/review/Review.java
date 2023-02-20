package com.ljh.review;

import java.util.Scanner;

import com.ljh.inquirycenter.InquiryService;

public class Review {
	
	public static void main(String[] args) throws Exception {
		//고객센터 서비스 불럴오기
		ReviewService rs = new ReviewService();
		rs.review();
	}

}
