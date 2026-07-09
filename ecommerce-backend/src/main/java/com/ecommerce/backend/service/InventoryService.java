package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Inventory;
import com.ecommerce.backend.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    public Inventory addStock(Inventory inventory){
       return inventoryRepository.save(inventory);
    }
    public List<Inventory> getAll(){
        return inventoryRepository.findAll();
    }
    public Inventory getByProduct(Long productId){
        return inventoryRepository.findByProductId(productId).orElseThrow(()-> new RuntimeException(" Inventory not found "));
    }
    public void reduceStock(Long productId,int quantity){
        Inventory inventory=getByProduct(productId);
        if(inventory.getQuantity()<quantity){
            throw new RuntimeException(" Out of stock ");
        }
        inventory.setQuantity(inventory.getQuantity()-quantity);
        inventoryRepository.save(inventory);
    }
    public void increaseStock(Long productId,int quantity){
        Inventory inventory=getByProduct(productId);
        inventory.setQuantity(inventory.getQuantity()+quantity);
        inventoryRepository.save(inventory);
    }
}
