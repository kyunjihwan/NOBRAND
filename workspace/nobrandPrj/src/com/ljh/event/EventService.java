package com.ljh.event;

import com.ljh.inquirycenter.Inquiry;
import com.ljh.inquirycenter.InquiryManager;
import com.main.Main;

public class EventService {

	public boolean startService() throws Exception {
		
		while(true) {
			System.out.println("\n1.뭔지모르겠음 2.이벤트수정 3.이벤트추가 4.이벤트삭제 5.이벤트목록 9.뒤로가기");
			String input = Main.SC.next();
			EventManager ev = new EventManager();
			
			if(input.equals("9")) {
				return true;
			}
			
			switch(input) {
			case "1" : ev.homepageAd(); break;
			case "2" : ev.updateEvent(); break;
			case "3" : ev.insertEvent(); break;
			case "4" : ev.deleteEvent(); break;
			case "5" : ev.showEventList(); break;
			default : System.out.println("다시 입력해주세요.");
			}
			return false;
		
		}
	}
}
