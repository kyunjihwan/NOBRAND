package com.ljh.inquirycenter;

import java.util.Scanner;

public class Inquiry {

	public static void main(String[] args) throws Exception {
			
			InquiryService is = new InquiryService();
			while(true) {
				boolean isFinish = is.startService();
				if(isFinish) {break;}
			}
		
	}

}
