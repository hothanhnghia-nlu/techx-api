package vn.edu.hcmuaf.fit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.api.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    OrderDetail findByOrderId(int orderId);
    OrderDetail findByOrderStatus(int status);
}
