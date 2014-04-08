package com.hackathon.guessprice.model;

public class ProductPriceRangeItem {
	private int startRange;
	private int endRange;
	private Double percent;
	private String rangeDesc;

	public String getRangeDesc() {
		return rangeDesc;
	}

	public void setRangeDesc(String rangeDesc) {
		this.rangeDesc = rangeDesc;
	}

	public int getStartRange() {
		return startRange;
	}

	public void setStartRange(int startRange) {
		this.startRange = startRange;
	}

	public int getEndRange() {
		return endRange;
	}

	public void setEndRange(int endRange) {
		this.endRange = endRange;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}
}
