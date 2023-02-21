package com.kjh.manager;

import com.kjh.Main;
import com.kjh.inquiry.InquiryData;

public class ManagerInquiryInput {

	public InquiryData insertInquiryAns() {
		System.out.println("===== 고객문의게시글 답변 =====");
		System.out.print("답변 내용 : ");
		String inqContent = Main.SC.nextLine();
		
		InquiryData data = new InquiryData();
		data.setInquiryContent(inqContent);
		
		return data;
		
	}
	
	public String removeInquiryAns() {
		System.out.println("===== 고객문의게시글 삭제 =====");
		System.out.print("게시글 번호(9. 뒤로가기) : ");
		String inqNum = Main.SC.nextLine();
		
		return inqNum;
	}
}
