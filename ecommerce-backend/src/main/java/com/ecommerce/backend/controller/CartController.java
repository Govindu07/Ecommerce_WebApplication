package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.CartItem;
import com.ecommerce.backend.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create/{cartId}")
    public Cart createCart(@PathVariable Long cartId){
        return cartService.createCart(cartId);
    }

    @PostMapping("/{cartId}/add/{productId}")
    public Cart addToCart(@PathVariable Long cartId,
                          @PathVariable Long productId,
                          @RequestParam int qty) {

        System.out.println("========== ADD TO CART HIT ==========");
        System.out.println("Cart ID = " + cartId);
        System.out.println("Product ID = " + productId);

        return cartService.addToCart(cartId, productId, qty);
    }

    @GetMapping("/{cartId}")
    public Cart getCart(@PathVariable Long cartId){

        return cartService.getCart(cartId);
    }
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<?> removeItem(
            @PathVariable Long cartItemId
    ) {

        cartService.removeItem(cartItemId);

        return ResponseEntity.ok("Item removed successfully");
    }
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<?> updateQuantity(
            @PathVariable Long cartItemId,
            @RequestParam int qty
    ){

        CartItem item = cartService.updateQuantity(cartItemId, qty);

        return ResponseEntity.ok(item);

    }
}
