package com.demo.spring.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="ORDER_ID")
	private String orderId;
	
	@Column(name="PRODUCT_ID")
	private String productId;
	
	
	private int quantity;
	
	@Column(name="STATUS")
	private String orderStatus; // PENDING, CREATED, CANCELLED

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String orderId, String productId, int quantity, String orderStatus) {
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.orderStatus = orderStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productId=" + productId + ", quantity=" + quantity + ", orderStatus="
				+ orderStatus + "]";
	}
	
	
}
