package com.deo.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deo.spring.entity.OrderEvent;

public interface OrderEventRepository extends MongoRepository<OrderEvent,String> {
}
