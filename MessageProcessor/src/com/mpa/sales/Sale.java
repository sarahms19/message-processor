package com.mpa.sales;

public class Sale {

	String messageType;
	String product;
	int price;
	int qty;
	String adjustment;

	public Sale(String messageType, String product, int price, int qty, String adjustment) {
		// TODO Auto-generated constructor stub
		this.messageType = messageType;
		this.product = product;
		this.price = price;
		this.qty = qty;
		this.adjustment = adjustment;
	}

	public Sale() {
		// TODO Auto-generated constructor stub
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getAdjustment() {
		return adjustment;
	}

	public void setAdjustment(String adjustment) {
		this.adjustment = adjustment;
	}

}
