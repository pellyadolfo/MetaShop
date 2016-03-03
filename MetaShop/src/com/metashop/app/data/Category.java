package com.metashop.app.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {

	private String name;

	public String getName() {
		return name;
	}

	public Category setName(String name) {
		this.name = name;
		return this;
	}
	
	private List<Brand> brands = new ArrayList<Brand>();
	
	public List<Brand> getBrands() {
		return brands;
	}

	public Category addBrand(Brand brand) {
		brands.add(brand);
		return this;
	}
	
}