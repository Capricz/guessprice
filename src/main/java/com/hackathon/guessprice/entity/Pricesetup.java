package com.hackathon.guessprice.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * The persistent class for the pricesetup database table.
 * 
 */
@Entity
public class Pricesetup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int priceSetupId;

	private BigDecimal setPrice;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productId")
	private Product product;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;

	public Pricesetup() {
	}

	public int getPriceSetupId() {
		return this.priceSetupId;
	}

	public void setPriceSetupId(int priceSetupId) {
		this.priceSetupId = priceSetupId;
	}

	public BigDecimal getSetPrice() {
		return this.setPrice;
	}

	public void setSetPrice(BigDecimal setPrice) {
		this.setPrice = setPrice;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}