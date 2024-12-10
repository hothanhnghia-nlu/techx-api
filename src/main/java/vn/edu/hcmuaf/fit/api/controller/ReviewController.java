package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.api.model.Review;
import vn.edu.hcmuaf.fit.api.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("api/v1/reviews")
@Tag(name = "Review Controller")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Create a new Review
    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestParam int productId,
                                               @ModelAttribute ReviewDTO review) {
        return new ResponseEntity<>(reviewService.saveReview(productId, review), HttpStatus.CREATED);
    }

    // Get all Review
    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getReviews();
    }

    // Get all Review by Product
    @GetMapping("/by-product")
    public List<ReviewDTO> getAllReviewsByProduct(@RequestParam("productId") int productId) {
        return reviewService.getReviewsByProduct(productId);
    }

    // Get Review by id
    @GetMapping("{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(reviewService.getReviewByID(id), HttpStatus.OK);
    }

    // Delete Review by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable ("id") int id) {
        reviewService.deleteReviewByID(id);
        return new ResponseEntity<>("Review " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
