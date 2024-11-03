package vn.edu.hcmuaf.fit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.api.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
