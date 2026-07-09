package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Inventory;
import com.ecommerce.backend.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public Inventory addStock(@RequestBody Inventory inventory){
        return inventoryService.addStock(inventory);
    }

    @GetMapping
    public List<Inventory> getAll(){
        return inventoryService.getAll();
    }

    @PutMapping("/{productId}")
    public Inventory getByProduct(@PathVariable Long productId){
        return inventoryService.getByProduct(productId);
    }

    @PutMapping("/reduce/{productId}")
    public String reduce(@PathVariable Long productId,@PathVariable int quantity){
        inventoryService.reduceStock(productId,quantity);
        return  " Stock reduced successfully ";
    }

    @PutMapping("/increase/{productId}")
    public String increase(@PathVariable Long productId, @PathVariable int quantity){
        inventoryService.increaseStock(productId,quantity);
        return " Stock Increased successfully ";
    }
}

