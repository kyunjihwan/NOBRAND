package com.ksk.orderCancel;

public class OrderCancelData {

	private String OrdCNo;
	private String OrdNo;
	private String OrdCDate;
	public String getOrdCNo() {
		return OrdCNo;
	}
	public void setOrdCNo(String ordCNo) {
		OrdCNo = ordCNo;
	}
	public String getOrdNo() {
		return OrdNo;
	}
	public void setOrdNo(String ordNo) {
		OrdNo = ordNo;
	}
	public String getOrdCDate() {
		return OrdCDate;
	}
	public void setOrdCDate(String ordCDate) {
		OrdCDate = ordCDate;
	}
	@Override
	public String toString() {
		return "OrderCancelData [OrdCNo=" + OrdCNo + ", OrdNo=" + OrdNo + ", OrdCDate=" + OrdCDate + "]";
	}
	public OrderCancelData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderCancelData(String ordCNo, String ordNo, String ordCDate) {
		super();
		OrdCNo = ordCNo;
		OrdNo = ordNo;
		OrdCDate = ordCDate;
	}
	
	
	
}
