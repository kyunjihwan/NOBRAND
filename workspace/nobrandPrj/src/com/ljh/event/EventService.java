package com.ljh.event;

import com.nobrand.main.Main;

public class EventService {
	
	public void event() throws Exception {
		while(true) {
			boolean isFinish = startService();
			if(isFinish) {break;}
		}
	}

	public boolean startService() throws Exception {
		
		while(true) {
			System.out.println("\n1.뭔지모르겠음 2.이벤트수정 3.이벤트추가 4.이벤트삭제 5.이벤트목록 9.뒤로가기");
			String input = Main.SC.next();
			EventShow es = new EventShow();
			if(input.equals("9")) {
				return true;
			}
			switch(input) {
			case "1" : es.homepageAd(); break;
			case "2" : es.updateEvent(); break;
			case "3" : es.insertEvent(); break;
			case "4" : es.deleteEvent(); break;
			case "5" : es.showEventList(); break;
			default : System.out.println("다시 입력해주세요.");
			}
			return false;
		}
	}
}
