package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.FavoriteDTO;
import vn.edu.hcmuaf.fit.api.model.Favorite;

import java.util.List;

public interface FavoriteService {
    Favorite saveFavorite(FavoriteDTO favoriteDTO);
    List<Favorite> getFavorites();
    Favorite getFavoriteByID(Integer id);
    Favorite updateFavoriteByID(Integer id, FavoriteDTO favoriteDTO);
    void deleteFavoriteByID(Integer id);

}
