package com.metashop.app.data;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String name;

	public String getName() {
		return name;
	}

	public Product setName(String name) {
		this.name = name;
		return this;
	}
	
	int price;
	
	public int getPrice() {
		return price;
	}

	public Product setPrice(int price) {
		this.price = price;
		return this;
	}
	
	String currency;

	public String getCurrency() {
		return currency;
	}

	public Product setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	
	boolean isSale = false;

	public boolean isSale() {
		return isSale;
	}

	public Product setSale(boolean isSale) {
		this.isSale = isSale;
		return this;
	};
	
	boolean isNew = false;

	public boolean isNew() {
		return isNew;
	}

	public Product setNew(boolean isNew) {
		this.isNew = isNew;
		return this;
	}
	
	String url;

	public String getUrl() {
		return url;
	}

	public Product setUrl(String url) {
		this.url = url;
		return this;
	}
}