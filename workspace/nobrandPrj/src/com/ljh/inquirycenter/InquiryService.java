package com.ljh.inquirycenter;

import com.main.Main;

public class InquiryService {
	
	public void inquiry() throws Exception {
		while(true) {
			boolean isFinish = startService();
			if(isFinish) {break;}
		}
	}
	
	public boolean startService() throws Exception {
		while(true) {
			System.out.println("");
			System.out.println("                        고객센터");
			System.out.println("");
			System.out.println("                         1. 문의글 작성");
			System.out.println("                         2. 문의글 삭제");
			System.out.println("                         3. 문의글 수정");
			System.out.println("                         4. 문의글 목록 조회");
			System.out.println("                         9. 뒤로가기");
			System.out.print("\n                         입력 >> ");
			
			String input = Main.SC.nextLine();
			InquiryShow im = new InquiryShow();
			if(input.equals("9")) {
				return true;
			}
			switch(input) {
			case "1" : im.writeBoard(); break;
			case "2" : im.deleteBoard(); break;
			case "3" : im.updateBoard(); break;
			case "4" : im.showAndRequestBoardList(); break;
			default : System.out.println("다시 입력해주세요.");
			}
			return false;
		}
	}
}
