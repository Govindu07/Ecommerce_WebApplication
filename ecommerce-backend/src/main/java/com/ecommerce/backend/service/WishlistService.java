package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Wishlist;
import com.ecommerce.backend.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public Wishlist save(Wishlist wishlist){
        return wishlistRepository.save(wishlist);
    }

    public List<Wishlist> getAll(){
        return wishlistRepository.findAll();
    }

    public void delete(Long id){
        wishlistRepository.deleteById(id);
    }
}
