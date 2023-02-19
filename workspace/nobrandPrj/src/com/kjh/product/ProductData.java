package com.kjh.product;

public class ProductData {

	private String prodName;
	private int prodPrice;
	private String prodContent;
	private String prodKind;
	private String prodColor;
	private String prodsize;

	public ProductData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductData(String prodName, int prodPrice, String prodContent, String prodKind, String prodColor,
			String prodsize) {
		super();
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.prodContent = prodContent;
		this.prodKind = prodKind;
		this.prodColor = prodColor;
		this.prodsize = prodsize;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getProdContent() {
		return prodContent;
	}

	public void setProdContent(String prodContent) {
		this.prodContent = prodContent;
	}

	public String getProdKind() {
		return prodKind;
	}

	public void setProdKind(String prodKind) {
		this.prodKind = prodKind;
	}

	public String getProdColor() {
		return prodColor;
	}

	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}

	public String getProdsize() {
		return prodsize;
	}

	public void setProdsize(String prodsize) {
		this.prodsize = prodsize;
	}

	@Override
	public String toString() {
		return "ProductData [prodName=" + prodName + ", prodPrice=" + prodPrice + ", prodContent=" + prodContent
				+ ", prodKind=" + prodKind + ", prodColor=" + prodColor + ", prodsize=" + prodsize + "]";
	}

}
