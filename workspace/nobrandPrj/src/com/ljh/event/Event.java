package com.ljh.event;

import java.util.Scanner;

import com.ljh.inquirycenter.InquiryService;
import com.main.Main;

public class Event {

	public static void main(String[] args) throws Exception {
		
		EventService es = new EventService();
		while(true) {
			boolean isFinish = es.startService();
			if(isFinish) {break;}
		}
		
	}

}
