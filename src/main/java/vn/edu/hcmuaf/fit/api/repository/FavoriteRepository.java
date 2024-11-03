package vn.edu.hcmuaf.fit.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.fit.api.model.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

}
