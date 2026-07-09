package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Cart;
import com.ecommerce.backend.entity.CartItem;
import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.CartItemRepository;
import com.ecommerce.backend.repository.CartRepository;
import com.ecommerce.backend.repository.ProductRepository;
import com.ecommerce.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;


    public CartService(
            CartRepository cartRepository,
            ProductRepository productRepository,
            UserRepository userRepository,
            CartItemRepository cartItemRepository
    ) {

        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;

    }


    // Create Cart for User
    public Cart createCart(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        Cart cart = new Cart();

        cart.setUser(user);

        return cartRepository.save(cart);

    }



    // Add Product to Cart
    public Cart addToCart(Long cartId, Long productId, int qty) {


        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart Not Found"));


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));


        CartItem existingItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);


        if(existingItem != null){

            existingItem.setQuantity(
                    existingItem.getQuantity() + qty
            );

        }
        else {

            CartItem item = new CartItem();

            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(qty);

            cart.getItems().add(item);

        }


        return cartRepository.save(cart);

    }



    // Get Cart
    public Cart getCart(Long cartId) {

        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart Not Found"));

    }



    // Remove Cart Item
    public void removeItem(Long cartItemId) {

        cartItemRepository.deleteById(cartItemId);

    }

    public CartItem updateQuantity(Long cartItemId, int qty){

        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart Item Not Found"));


        item.setQuantity(qty);


        return cartItemRepository.save(item);

    }

}