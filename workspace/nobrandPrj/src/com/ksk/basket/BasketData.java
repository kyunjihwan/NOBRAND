package com.ksk.basket;

public class BasketData {

	private String BasketNo;
	private String ProdNo;
	private String BasketDate;
	private String BuyYn;
	private String Letter;
	private String LetterPotion;
	public String getBasketNo() {
		return BasketNo;
	}
	public void setBasketNo(String basketNo) {
		BasketNo = basketNo;
	}
	public String getProdNo() {		return ProdNo;
	}
	public void setProdNo(String prodNo) {
		ProdNo = prodNo;
	}
	public String getBasketDate() {
		return BasketDate;
	}
	public void setBasketDate(String basketDate) {
		BasketDate = basketDate;
	}
	public String getBuyYn() {
		return BuyYn;
	}
	public void setBuyYn(String buyYn) {
		BuyYn = buyYn;
	}
	public String getLetter() {
		return Letter;
	}
	public void setLetter(String letter) {
		Letter = letter;
	}
	public String getLetterPotion() {
		return LetterPotion;
	}
	public void setLetterPotion(String LetterPotion) {
		LetterPotion = LetterPotion;
	}
	@Override
	public String toString() {
		return "BasketData [BasketNo=" + BasketNo + ", ProdNo=" + ProdNo + ", BasketDate=" + BasketDate + ", BuyYn="
				+ BuyYn + ", Letter=" + Letter + ", LetterPotion=" + LetterPotion + "]";
	}
	public BasketData(String basketNo, String prodNo, String basketDate, String buyYn, String letter,
			String LetterPotion) {
		super();
		BasketNo = basketNo;
		ProdNo = prodNo;
		BasketDate = basketDate;
		BuyYn = buyYn;
		Letter = letter;
		LetterPotion = LetterPotion;
	}
	public BasketData() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
}//class
