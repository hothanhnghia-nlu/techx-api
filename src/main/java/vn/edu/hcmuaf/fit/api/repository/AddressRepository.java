package vn.edu.hcmuaf.fit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.api.model.Address;
import vn.edu.hcmuaf.fit.api.model.Cart;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByUserId(int customerId);
}
