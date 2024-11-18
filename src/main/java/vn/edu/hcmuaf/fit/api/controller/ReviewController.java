package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.api.model.Review;
import vn.edu.hcmuaf.fit.api.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Create a new Comment
    @PostMapping()
    public ResponseEntity<Review> createComment(@RequestBody ReviewDTO comment) {
        return new ResponseEntity<>(reviewService.saveComment(comment), HttpStatus.CREATED);
    }

    // Get all Comment
    @GetMapping
    public List<Review> getAllComments() {
        return reviewService.getComments();
    }

    // Get Comment by id
    @GetMapping("{id}")
    public ResponseEntity<Review> getCommentById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(reviewService.getCommentByID(id), HttpStatus.OK);
    }

    // Update Comment by id
    @PutMapping("{id}")
    public ResponseEntity<Review> updateCommentById(@PathVariable ("id") int id,
                                                    @RequestBody ReviewDTO reviewDTO) {
        return new ResponseEntity<>(reviewService.updateCommentByID(id, reviewDTO), HttpStatus.OK);
    }

    // Delete Comment by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable ("id") int id) {
        reviewService.deleteCommentByID(id);
        return new ResponseEntity<>("Comment " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
