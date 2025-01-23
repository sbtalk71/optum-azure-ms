package com.demo.spring.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.demo.spring.query.Product;
import com.demo.spring.query.ProductRepository;

@Service
public class EventStoreListener {

    @Autowired
    private ProductRepository productRepository;

    @KafkaListener(topics = "product-events", groupId = "inventory-group")
    public void listen(Event event) {
    	System.out.println("Product Event received...");
        if (event instanceof ProductCreatedEvent) {
            ProductCreatedEvent productEvent = (ProductCreatedEvent) event;
            Product product = new Product(productEvent.getProductId(), productEvent.getName(), 0);
            productRepository.save(product);
        } else if (event instanceof ProductStockUpdatedEvent) {
            ProductStockUpdatedEvent stockEvent = (ProductStockUpdatedEvent) event;
            productRepository.findById(stockEvent.getProductId()).ifPresent(product -> {
                product.setStock(product.getStock() + stockEvent.getQuantity());
                productRepository.save(product);
            });
        }
    }
}
