package com.ksk.basketlist;

public class BasketlistData {

	private String BasketlistNo;
	private String MemberNo;
	private String BasketNo;
	public String getBasketlistNo() {
		return BasketlistNo;
	}
	public void setBasketlistNo(String basketlistNo) {
		BasketlistNo = basketlistNo;
	}
	public String getMemberNo() {
		return MemberNo;
	}
	public void setMemberNo(String memberNo) {
		MemberNo = memberNo;
	}
	public String getBasketNo() {
		return BasketNo;
	}
	public void setBasketNo(String basketNo) {
		BasketNo = basketNo;
	}
	@Override
	public String toString() {
		return "BasketlistData [BasketlistNo=" + BasketlistNo + ", MemberNo=" + MemberNo + ", BasketNo=" + BasketNo
				+ "]";
	}
	public BasketlistData(String basketlistNo, String memberNo, String basketNo) {
		super();
		BasketlistNo = basketlistNo;
		MemberNo = memberNo;
		BasketNo = basketNo;
	}
	public BasketlistData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
