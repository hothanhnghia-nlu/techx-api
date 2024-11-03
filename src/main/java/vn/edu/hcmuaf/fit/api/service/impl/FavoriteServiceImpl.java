package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.FavoriteDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Favorite;
import vn.edu.hcmuaf.fit.api.repository.FavoriteRepository;
import vn.edu.hcmuaf.fit.api.service.FavoriteService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @Override
    public Favorite saveFavorite(FavoriteDTO favoriteDTO) {
        Favorite favorite = new Favorite();
        favorite.setId(favoriteDTO.getId());
//        Favorite.setName(FavoriteDTO.getName());
        favorite.setStatus((byte) 1);
        favorite.setCreatedAt(LocalDateTime.now());

        return favoriteRepository.save(favorite);
    }

    @Override
    public List<Favorite> getFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite getFavoriteByID(Integer id) {
        return favoriteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Favorite", "Id", id));
    }

    @Override
    public Favorite updateFavoriteByID(Integer id, FavoriteDTO favoriteDTO) {
        Favorite existingFavorite = favoriteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Favorite", "Id", id));

//        existingFavorite.setName(FavoriteDTO.getName() != null ? FavoriteDTO.getName() : existingFavorite.getName());
        existingFavorite.setStatus(favoriteDTO.getStatus() != 0 ? favoriteDTO.getStatus() : existingFavorite.getStatus());

        return favoriteRepository.save(existingFavorite);
    }

    @Override
    public void deleteFavoriteByID(Integer id) {
        favoriteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Favorite", "Id", id));

        favoriteRepository.deleteById(id);
    }
}
