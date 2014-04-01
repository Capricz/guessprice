package com.hackathon.guessprice.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int productId;

	private BigDecimal highPrice;

	private BigDecimal lowPrice;

	private BigDecimal marketPrice;

	private String productDescription;

	private String productLine;

	private String productName;

	//bi-directional many-to-one association to Pricesetup
	@OneToMany(mappedBy="product")
	private List<Pricesetup> pricesetups;

	public Product() {
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public BigDecimal getHighPrice() {
		return this.highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getLowPrice() {
		return this.lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public BigDecimal getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getProductDescription() {
		return this.productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductLine() {
		return this.productLine;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Pricesetup> getPricesetups() {
		return this.pricesetups;
	}

	public void setPricesetups(List<Pricesetup> pricesetups) {
		this.pricesetups = pricesetups;
	}

	
	public Pricesetup addPricesetups(Pricesetup pricesetups) {
		getPricesetups().add(pricesetups);
		pricesetups.setProduct(this);

		return pricesetups;
	}

	public Pricesetup removePricesetups(Pricesetup pricesetups) {
		getPricesetups().remove(pricesetups);
		pricesetups.setProduct(null);

		return pricesetups;
	}
}