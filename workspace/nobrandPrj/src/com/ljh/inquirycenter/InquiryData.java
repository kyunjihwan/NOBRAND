package com.ljh.inquirycenter;

public class InquiryData {

	private String id;
	private String title;
	private String content;
	private String no;

	public InquiryData(String id, String title, String content, String no) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.no = no;
	}

	public InquiryData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InquirycenterData [id=" + id + ", title=" + title + ", content=" + content + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}
