package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.api.model.Review;

import java.util.List;

public interface ReviewService {
    Review saveReview(int productId, ReviewDTO reviewDTO);
    List<ReviewDTO> getReviews();
    List<ReviewDTO> getReviewsByProduct(int productId);
    Review getReviewByID(Integer id);
    void deleteReviewByID(Integer id);

}
