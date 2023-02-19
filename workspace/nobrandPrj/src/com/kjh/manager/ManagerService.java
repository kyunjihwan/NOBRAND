package com.kjh.manager;

public class ManagerService {

	ManagerProduct mp = new ManagerProduct();
	ManagerKind mk = new ManagerKind();
	ManagerInquiry mi = new ManagerInquiry();
	
	public void insertProduct() throws Exception {
		mp.insertProduct();
	}
	
	public void removeProduct() throws Exception {
		mp.removeProduct();
	}
	
	public void insertKind() throws Exception {
		mk.insertKind();
	}
	
	public void removeKind() throws Exception {
		mk.removeKind();
	}
	
	public void insertInquiryAns() throws Exception {
		mi.insertInquiryAns();
	}
	
	public void removeInquiryAns() throws Exception {
		mi.removeInquiryAns();
	}
	
}
