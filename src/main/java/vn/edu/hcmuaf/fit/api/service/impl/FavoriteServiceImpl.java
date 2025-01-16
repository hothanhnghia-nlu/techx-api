package vn.edu.hcmuaf.fit.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.*;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.repository.FavoriteRepository;
import vn.edu.hcmuaf.fit.api.repository.ProductRepository;
import vn.edu.hcmuaf.fit.api.repository.ProviderRepository;
import vn.edu.hcmuaf.fit.api.repository.UserRepository;
import vn.edu.hcmuaf.fit.api.service.AuthenticationService;
import vn.edu.hcmuaf.fit.api.service.FavoriteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Favorite saveFavorite(int productId, FavoriteDTO favoriteDTO) {
        int userId = authenticationService.getCurrentUserId();

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", favoriteDTO.getId()));

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", favoriteDTO.getId()));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        favorite.setStatus((byte) 1);
        favorite.setCreatedAt(LocalDateTime.now());

        return favoriteRepository.save(favorite);
    }

    @Override
    public List<FavoriteDTO> getAllFavorites() {
        List<Favorite> favorites = favoriteRepository.findAll();

        return favorites.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<FavoriteDTO> getFavoriteByUser() {
        int id = authenticationService.getCurrentUserId();
        List<Favorite> favorites = favoriteRepository.findByUserId(id);
        return favorites.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private FavoriteDTO convertToDTO(Favorite favorite) {
        UserDTO userDTO = null;
        ProductDTO productDTO = null;
        User user = favorite.getUser();
        Product product = favorite.getProduct();

        if (user != null) {
            userDTO = new UserDTO(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber()
            );
        }

        if (product != null) {
            List<ImageDTO> imageDTOList = product.getImages().stream()
                    .map(image -> new ImageDTO(
                            image.getId(),
                            image.getName(),
                            image.getUrl()
                    ))
                    .collect(Collectors.toList());

            productDTO = new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getOriginalPrice(),
                    product.getNewPrice(),
                    product.getColor(),
                    product.getRam(),
                    product.getStorage(),
                    imageDTOList
            );
        }

        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setId(favorite.getId());
        favoriteDTO.setUser(userDTO);
        favoriteDTO.setProduct(productDTO);
        favoriteDTO.setStatus(favorite.getStatus());
        favoriteDTO.setCreatedAt(favorite.getCreatedAt());

        return favoriteDTO;
    }

    @Override
    public Favorite getFavoriteByID(Integer id) {
        return favoriteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Favorite", "Id", id));
    }

    @Override
    public void deleteFavoriteByID(Integer id) {
        favoriteRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Favorite", "Id", id));

        int userId = authenticationService.getCurrentUserId();

        userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        favoriteRepository.deleteById(id);
    }

    @Override
    public void deleteFavoriteByProduct(int productId) {
        int userId = authenticationService.getCurrentUserId();

        Favorite favorite = favoriteRepository.findByUserIdAndProductId(userId, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite", "Product id", productId));

        favoriteRepository.delete(favorite);
    }
}
