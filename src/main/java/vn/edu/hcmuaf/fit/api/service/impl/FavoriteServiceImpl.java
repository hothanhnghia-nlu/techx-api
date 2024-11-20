package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.FavoriteDTO;
import vn.edu.hcmuaf.fit.api.dto.ImageDTO;
import vn.edu.hcmuaf.fit.api.dto.ProductDTO;
import vn.edu.hcmuaf.fit.api.dto.UserDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.repository.FavoriteRepository;
import vn.edu.hcmuaf.fit.api.repository.ProductRepository;
import vn.edu.hcmuaf.fit.api.repository.ProviderRepository;
import vn.edu.hcmuaf.fit.api.repository.UserRepository;
import vn.edu.hcmuaf.fit.api.service.FavoriteService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Favorite saveFavorite(int userId, int productId, FavoriteDTO favoriteDTO) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", favoriteDTO.getId()));

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", favoriteDTO.getId()));

        Favorite favorite = new Favorite();
        favorite.setId(favoriteDTO.getId());
        favorite.setUser(user);
        favorite.setProduct(product);
        favorite.setStatus((byte) 1);
        favorite.setCreatedAt(LocalDateTime.now());

        return favoriteRepository.save(favorite);
    }

    @Override
    public List<FavoriteDTO> getFavorites() {
        List<Favorite> favorites = favoriteRepository.findAll();

        return favorites.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private FavoriteDTO convertToDTO(Favorite favorite) {
        UserDTO userDTO = null;
        ProductDTO productDTO = null;
        ImageDTO imageDTO;
        User user = favorite.getUser();
        Product product = favorite.getProduct();
        List<ImageDTO> imageDTOList = new ArrayList<>();

        if (user != null) {
            userDTO = new UserDTO(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber()
            );
        }

        if (product != null) {
            if (product.getImages() != null) {
                for (Image image : product.getImages()) {
                    imageDTO = new ImageDTO(
                            image.getId(),
                            image.getName(),
                            image.getUrl()
                    );
                    imageDTOList.add(imageDTO);
                }
            }
            productDTO = new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getOriginalPrice(),
                    product.getNewPrice(),
                    product.getColor(),
                    product.getRam(),
                    product.getStorage()
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

        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));

        productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));

        favoriteRepository.deleteById(id);
    }
}
