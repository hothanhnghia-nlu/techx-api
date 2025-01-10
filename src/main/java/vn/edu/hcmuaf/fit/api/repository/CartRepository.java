package vn.edu.hcmuaf.fit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.api.model.Cart;
import vn.edu.hcmuaf.fit.api.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(int userId);
    @Transactional // Đảm bảo transaction được bật cho phương thức này
    @Modifying// Chỉ định đây là thao tác ghi (update/delete)
    void deleteAllByUserId(int userId);
    Optional<Cart> findByUserIdAndProductId(int userId, int productId);
}
