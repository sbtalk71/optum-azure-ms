package com.deo.spring.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.deo.spring.entity.OrderEvent;

@Component
public class OrderEventKafkaPublisher {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Value("${order.event.topicName}")
    private String topicName;


    public void sendOrderEvent(OrderEvent orderEvent) {
        kafkaTemplate.send(topicName, orderEvent);
    }
}
