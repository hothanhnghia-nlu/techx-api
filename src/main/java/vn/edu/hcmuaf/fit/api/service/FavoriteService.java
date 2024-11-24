package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.FavoriteDTO;
import vn.edu.hcmuaf.fit.api.model.Favorite;

import java.util.List;

public interface FavoriteService {
    Favorite saveFavorite(int userId, int productId, FavoriteDTO favoriteDTO);
    List<FavoriteDTO> getFavorites();
    Favorite getFavoriteByID(Integer id);
    void deleteFavoriteByID(Integer id);

}
