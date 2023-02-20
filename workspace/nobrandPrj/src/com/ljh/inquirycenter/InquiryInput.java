package com.ljh.inquirycenter;

import com.main.Main;

public class InquiryInput {
	
	private InquiryService is = new InquiryService();
	
	public InquiryData writeBoardInput() throws Exception {
		System.out.print("\n아이디(뒤로가기:9) : ");
		String id = Main.SC.nextLine();
		if(id.equals("9")) {
			System.out.println("작성 취소");
			is.startService();
		}
		Main.SC.nextLine();
		System.out.print("제목(뒤로가기:9) : ");
		String title = Main.SC.nextLine();
		if(title.equals("9")) {
			System.out.println("작성 취소");
			is.startService();
		}
		System.out.print("내용(뒤로가기:9) : ");
		String content = Main.SC.nextLine();
		if(content.equals("9")) {
			System.out.println("작성 취소");
			is.startService();
		}
		InquiryData data = new InquiryData();
		data.setId(id);
		data.setTitle(title);
		data.setContent(content);
		return data;
	}
	
	

}
