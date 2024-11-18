package vn.edu.hcmuaf.fit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.api.model.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findByOrderById();
}
