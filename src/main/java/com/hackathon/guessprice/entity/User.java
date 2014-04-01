package com.hackathon.guessprice.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;

	private String password;

	private String region;

	private int role;

	private String userName;

	//bi-directional many-to-one association to Pricesetup
	@OneToMany(mappedBy="user")
	private List<Pricesetup> pricesetups;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Pricesetup> getPricesetups() {
		return this.pricesetups;
	}

	public void setPricesetups(List<Pricesetup> pricesetups) {
		this.pricesetups = pricesetups;
	}

	
	public Pricesetup addPricesetups(Pricesetup pricesetups) {
		getPricesetups().add(pricesetups);
		pricesetups.setUser(this);

		return pricesetups;
	}

	public Pricesetup removePricesetups(Pricesetup pricesetups) {
		getPricesetups().remove(pricesetups);
		pricesetups.setUser(null);

		return pricesetups;
	}
}