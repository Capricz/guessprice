package com.hackathon.guessprice.model;

public class SetPriceDto {
	private int userId;
	private int productId;
	private double price;

	public double getPrice() {
		return price;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
