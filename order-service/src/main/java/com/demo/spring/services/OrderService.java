package com.demo.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring.dao.Order;
import com.demo.spring.dao.OrderRepository;
import com.demo.spring.exceptions.OrderExistsException;
import com.demo.spring.exceptions.OrderNotFoundException;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	public List<Order> listAllOrders(){
		return orderRepository.findAll();
	}
	
	public Order getOrderById(String orderId) {
		return orderRepository.findByOrderId(orderId).orElseThrow(()->new OrderNotFoundException("Order Not Found with id "+orderId));
	}
	
	public Order saveOrder(Order order) {
		if(orderRepository.findByOrderId(order.getOrderId()).isPresent()) {
			throw new OrderExistsException("Order exists");
		}else {
			return orderRepository.save(order);
		}
	}
}
