package com.kjh.inquiry;

public class InquiryData {

	private String inquiryNo;
	private String inquiryContent;

	public InquiryData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InquiryData(String inquiryNo, String inquiryContent) {
		super();
		this.inquiryNo = inquiryNo;
		this.inquiryContent = inquiryContent;
	}

	public String getInquiryNo() {
		return inquiryNo;
	}

	public void setInquiryNo(String inquiryNo) {
		this.inquiryNo = inquiryNo;
	}

	public String getInquiryContent() {
		return inquiryContent;
	}

	public void setInquiryContent(String inquiryContent) {
		this.inquiryContent = inquiryContent;
	}

	@Override
	public String toString() {
		return "InquiryData [inquiryNo=" + inquiryNo + ", inquiryContent=" + inquiryContent + "]";
	}

}
