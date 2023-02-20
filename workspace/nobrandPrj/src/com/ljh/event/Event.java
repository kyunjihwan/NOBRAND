package com.ljh.event;

import java.util.Scanner;

import com.ljh.inquirycenter.InquiryService;
import com.main.Main;

public class Event {

	public static void main(String[] args) throws Exception {
		//이벤트 서비스 불러오기 
		EventService es = new EventService();
		es.event();
	}

}
