package com.sys;

public class MemberData {
	
	private String memberNo;
	private String memberId;
	private String memberPwd;
	private String memberNick;
	private String memberAdr;
	private String memberPh;
	private String hintContent;
	private String answerHint;
	private String managerNo;
	private String managerId;
	private String managerPwd;
	private String managerNick;
	private String productNo;
	private String withDrawalYN;

	
	
	
	
	
	
	

	

	public MemberData(String memberNo, String memberId, String memberPwd, String memberNick, String memberAdr,
			String memberPh, String hintContent, String answerHint, String managerNo, String managerId,
			String managerPwd, String managerNick, String productNo, String withDrawalYN) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNick = memberNick;
		this.memberAdr = memberAdr;
		this.memberPh = memberPh;
		this.hintContent = hintContent;
		this.answerHint = answerHint;
		this.managerNo = managerNo;
		this.managerId = managerId;
		this.managerPwd = managerPwd;
		this.managerNick = managerNick;
		this.productNo = productNo;
		this.withDrawalYN = withDrawalYN;
	}



	public String getWithDrawalYN() {
		return withDrawalYN;
	}



	public void setWithDrawalYN(String withDrawalYN) {
		this.withDrawalYN = withDrawalYN;
	}



	public String getProductNo() {
		return productNo;
	}


	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}


	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getManagerNo() {
		return managerNo;
	}
	
	public void setManagerNo(String managerNo) {
		this.managerNo = managerNo;
	}
	
	public String getManagerId() {
		return managerId;
	}
	
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	
	public String getManagerPwd() {
		return managerPwd;
	}
	
	public void setManagerPwd(String managerPwd) {
		this.managerPwd = managerPwd;
	}
	
	public String getManagerNick() {
		return managerNick;
	}
	
	public void setManagerNick(String managerNick) {
		this.managerNick = managerNick;
	}
	
	public String getMemberAdr() {
		return memberAdr;
	}
	public void setMemberAdr(String memberAdr) {
		this.memberAdr = memberAdr;
	}
	public String getMemberPh() {
		return memberPh;
	}
	public void setMemberPh(String memberPh) {
		this.memberPh = memberPh;
	}
	public String getHintContent() {
		return hintContent;
	}
	public void setHintContent(String hintContent) {
		this.hintContent = hintContent;
	}
	public String getAnswerHint() {
		return answerHint;
	}
	public void setAnswerHint(String answerHint) {
		this.answerHint = answerHint;
	}
	public MemberData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	@Override
	public String toString() {
		return "MemberData [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberNick=" + memberNick
				+ ", memberAdr=" + memberAdr + ", memberPh=" + memberPh + ", hintContent=" + hintContent
				+ ", answerHint=" + answerHint + ", managerNo=" + managerNo + ", managerId=" + managerId
				+ ", managerPwd=" + managerPwd + ", managerNick=" + managerNick + "]";
	}
	
	

}
