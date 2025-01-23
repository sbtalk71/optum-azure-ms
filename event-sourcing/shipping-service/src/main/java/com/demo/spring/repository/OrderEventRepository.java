package com.demo.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.spring.entity.OrderEvent;

public interface OrderEventRepository extends MongoRepository<OrderEvent,String> {
}
