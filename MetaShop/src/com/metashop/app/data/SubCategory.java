package com.metashop.app.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubCategory implements Serializable {

	private String name;

	public String getName() {
		return name;
	}

	public SubCategory setName(String name) {
		this.name = name;
		return this;
	}
	
	private List<Product> products = new ArrayList<Product>();
	
	public List<Product> getProduct() {
		return products;
	}

	public SubCategory addProduct(Product product) {
		products.add(product);
		return this;
	}
}