package com.demo.spring.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commands")
public class CommandController {

    @Autowired
    private CommandHandler commandHandler;

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestParam String id, @RequestParam String name) {
        commandHandler.handleCreateProduct(id, name);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-stock")
    public ResponseEntity<Void> updateStock(@RequestParam String id, @RequestParam int quantity) {
        commandHandler.handleUpdateStock(id, quantity);
        return ResponseEntity.ok().build();
    }
}
