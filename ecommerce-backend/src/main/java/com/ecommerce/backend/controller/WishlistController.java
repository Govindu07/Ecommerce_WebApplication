package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Wishlist;
import com.ecommerce.backend.service.WishlistService;
import jakarta.persistence.GeneratedValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping
    public Wishlist addToWishlist( @RequestBody Wishlist wishlist){
        return wishlistService.save(wishlist);
    }

    @GetMapping
    public List<Wishlist> getWishlist(){
        return  wishlistService.getAll();
    }

    @DeleteMapping("/{id}")
    public String removeFromWishlist(@PathVariable Long id){
        wishlistService.delete(id);
        return " wishlist deleted successfully ";
    }
}
