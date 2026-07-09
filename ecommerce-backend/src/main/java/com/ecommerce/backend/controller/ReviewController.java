package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Review;
import com.ecommerce.backend.service.ReviewService;
import jakarta.persistence.GeneratedValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review addReview( @RequestBody Review review){
        return reviewService.save(review);
    }

    @GetMapping("/{id}")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
        return " Review deleted successfully ";
    }

}
