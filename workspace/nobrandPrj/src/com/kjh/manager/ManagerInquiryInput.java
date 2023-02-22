package com.kjh.manager;

import com.kjh.Main;
import com.kjh.inquiry.InquiryData;

public class ManagerInquiryInput {

	public InquiryData insertInquiryAns() {
		System.out.println("                ────────── 게시글 답변 ──────────");
		System.out.println("                           답변 내용 ");
		System.out.print("                       입력 >> ");
		String inqContent = Main.SC.nextLine();
		
		InquiryData data = new InquiryData();
		data.setInquiryContent(inqContent);
		
		return data;
		
	}
	
	public String removeInquiryAns() {
		System.out.println("                ────────── 게시글 삭제 ──────────");
		System.out.println("                     게시글 번호 삭제(9. 뒤로가기)");
		System.out.print("                        입력 >> ");
		String inqNum = Main.SC.nextLine();
		
		return inqNum;
	}
}
