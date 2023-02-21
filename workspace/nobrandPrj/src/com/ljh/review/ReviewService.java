package com.ljh.review;

import com.main.Main;

public class ReviewService {
	
	public void review() throws Exception {
		while(true) {
			boolean isFinish = startService();
			if(isFinish) {break;}
		}
	}
	
	public boolean startService() throws Exception {
		
		System.out.println("                   리뷰");
		System.out.println("");
		System.out.println("1. 리뷰 작성");
		System.out.println("2. 리뷰 삭제");
		System.out.println("3. 리뷰 수정");
		System.out.println("4. 리뷰글 목록 조회");
		System.out.println("9. 뒤로가기");
		System.out.print("\n메뉴를 선택하세요 : ");
		
		String input = Main.SC.nextLine();
		ReviewShow rs = new ReviewShow();
		if(input.equals("9")) {
			return true;
		}
		switch(input) {
		case "1" : rs.writeReview(); break;
		case "2" : rs.deleteReview(); break;
		case "3" : rs.updateReview(); break;
		case "4" : rs.showAndRequestList(); break;
		default : System.out.println("다시 입력해주세요.");
		}
		return false;
	}

}
