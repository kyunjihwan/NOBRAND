package com.ksk.orderProduct;

public class OrderProductData {

	private String OrderNo;
	private String BasketlistNo;
	private String OrdpriceTotal;
	private String OrdDate;
	private String OrdAdr;
	private String OrdPh;
	@Override
	public String toString() {
		return "OrderProductData [OrderNo=" + OrderNo + ", BasketlistNo=" + BasketlistNo + ", OrdpriceTotal="
				+ OrdpriceTotal + ", OrdDate=" + OrdDate + ", OrdAdr=" + OrdAdr + ", OrdPh=" + OrdPh + "]";
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getBasketlistNo() {
		return BasketlistNo;
	}
	public void setBasketlistNo(String basketlistNo) {
		BasketlistNo = basketlistNo;
	}
	public String getOrdpriceTotal() {
		return OrdpriceTotal;
	}
	public void setOrdpriceTotal(String ordpriceTotal) {
		OrdpriceTotal = ordpriceTotal;
	}
	public String getOrdDate() {
		return OrdDate;
	}
	public void setOrdDate(String ordDate) {
		OrdDate = ordDate;
	}
	public String getOrdAdr() {
		return OrdAdr;
	}
	public void setOrdAdr(String ordAdr) {
		OrdAdr = ordAdr;
	}
	public String getOrdPh() {
		return OrdPh;
	}
	public void setOrdPh(String ordPh) {
		OrdPh = ordPh;
	}
	public OrderProductData(String orderNo, String basketlistNo, String ordpriceTotal, String ordDate, String ordAdr,
			String ordPh) {
		super();
		OrderNo = orderNo;
		BasketlistNo = basketlistNo;
		OrdpriceTotal = ordpriceTotal;
		OrdDate = ordDate;
		OrdAdr = ordAdr;
		OrdPh = ordPh;
	}
	public OrderProductData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
