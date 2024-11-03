package vn.edu.hcmuaf.fit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.api.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Integer> {

}
