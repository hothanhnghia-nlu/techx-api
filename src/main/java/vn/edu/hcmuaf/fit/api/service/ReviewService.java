package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.ReviewDTO;
import vn.edu.hcmuaf.fit.api.model.Review;

import java.util.List;

public interface ReviewService {
    Review saveComment(ReviewDTO reviewDTO);
    List<Review> getComments();
    Review getCommentByID(Integer id);
    Review updateCommentByID(Integer id, ReviewDTO reviewDTO);
    void deleteCommentByID(Integer id);

}
