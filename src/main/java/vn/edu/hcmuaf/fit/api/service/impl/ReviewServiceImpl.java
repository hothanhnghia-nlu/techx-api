package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Review;
import vn.edu.hcmuaf.fit.api.repository.ReviewRepository;
import vn.edu.hcmuaf.fit.api.service.ReviewService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review saveComment(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
//        Comment.setName(CommentDTO.getName());
        review.setStatus((byte) 1);
        review.setCreatedAt(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getComments() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getCommentByID(Integer id) {
        return reviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "Id", id));
    }

    @Override
    public Review updateCommentByID(Integer id, ReviewDTO reviewDTO) {
        Review existingReview = reviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "Id", id));

//        existingComment.setName(CommentDTO.getName() != null ? CommentDTO.getName() : existingComment.getName());
        existingReview.setStatus(reviewDTO.getStatus() != 0 ? reviewDTO.getStatus() : existingReview.getStatus());
        existingReview.setUpdatedAt(LocalDateTime.now());

        return reviewRepository.save(existingReview);
    }

    @Override
    public void deleteCommentByID(Integer id) {
        reviewRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "Id", id));

        reviewRepository.deleteById(id);
    }
}
