package com.metashop.app.data;

import java.io.Serializable;

public class Brand implements Serializable {

	String name;

	public String getName() {
		return name;
	}

	public Brand setName(String name) {
		this.name = name;
		return this;
	}
	
	int count;
	
	public int getCount() {
		return count;
	}

	public Brand setCount(int count) {
		this.count = count;
		return this;
	}
}
