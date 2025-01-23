package com.example.eventsourcing.controller;

import com.example.eventsourcing.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String createOrder(@RequestParam String orderId, @RequestParam String product, @RequestParam int quantity) {
        orderService.createOrder(orderId, product, quantity);
        return "Order created successfully!";
    }
}
