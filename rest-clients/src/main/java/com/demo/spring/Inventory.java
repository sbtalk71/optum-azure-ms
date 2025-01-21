package com.demo.spring;



public class Inventory {
	
	
	private Long id;
	
	
	private String productId;
	
	
	private String productName;
	
	private int stock;
	
	
	private double price;

	public Inventory() {
		// TODO Auto-generated constructor stub
	}

	public Inventory(String productId, String productName, int stock, double price) {
		this.productId = productId;
		this.productName = productName;
		this.stock = stock;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return productId + " " + productName + " " + stock;
	}
}
