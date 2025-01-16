package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.FavoriteDTO;
import vn.edu.hcmuaf.fit.api.dto.FavoriteDTO;
import vn.edu.hcmuaf.fit.api.model.Favorite;

import java.util.List;

public interface FavoriteService {
    Favorite saveFavorite(int productId, FavoriteDTO favoriteDTO);
    List<FavoriteDTO> getAllFavorites();
    List<FavoriteDTO> getFavoriteByUser();
    Favorite getFavoriteByID(Integer id);
    void deleteFavoriteByID(Integer id);
    void deleteFavoriteByProduct(int productId);

}
