package vn.edu.hcmuaf.fit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.api.model.Cart;
import vn.edu.hcmuaf.fit.api.model.Favorite;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserId(int userId);
    Optional<Favorite> findByUserIdAndProductId(int userId, int productId);
}
