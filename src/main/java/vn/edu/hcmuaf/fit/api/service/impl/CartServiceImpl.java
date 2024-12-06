package vn.edu.hcmuaf.fit.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.api.dto.*;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.repository.CartRepository;
import vn.edu.hcmuaf.fit.api.repository.ProductRepository;
import vn.edu.hcmuaf.fit.api.repository.UserRepository;
import vn.edu.hcmuaf.fit.api.service.AuthenticationService;
import vn.edu.hcmuaf.fit.api.service.CartService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Cart saveCart(int productId) {
        int userId = authenticationService.getCurrentUserId();

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", productId));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(1);
        cart.setPrice(product.getNewPrice());
        cart.setStatus((byte) 1);
        cart.setOrderDate(LocalDateTime.now());

        return cartRepository.save(cart);
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CartDTO> getCartByUser() {
        int id = authenticationService.getCurrentUserId();
        List<Cart> carts = cartRepository.findByUserId(id);
        return carts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CartDTO convertToDTO(Cart cart) {
        UserDTO userDTO = null;
        ProductDTO productDTO = null;

        if (cart.getUser() != null) {
            User user = cart.getUser();
            userDTO = new UserDTO(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber()
            );
        }

        if (cart.getProduct() != null) {
            Product product = cart.getProduct();
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

        return new CartDTO(
                cart.getId(),
                userDTO,
                productDTO,
                cart.getQuantity(),
                cart.getPrice(),
                cart.getStatus(),
                cart.getOrderDate()
        );
    }

    @Override
    public Cart getCartByID(Integer id) {
        return cartRepository.findById(authenticationService.getCurrentUserId()).orElseThrow(() ->
                new ResourceNotFoundException("Cart", "Id", id));
    }

    @Override
    public int getQuantityInCart() {
        int id = authenticationService.getCurrentUserId();
        if (id != 0) {
            return getCartByUser().stream()
                    .mapToInt(CartDTO::getQuantity)
                    .sum();
        } else {
            return 0;
        }
    }

    @Override
    public Cart updateCartByID(Integer id, CartDTO cartDTO) {
        Cart existingCart = cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart", "Id", id));

        Product product = productRepository.findById(cartDTO.getProduct().getId()).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", cartDTO.getProduct().getId()));

        existingCart.setProduct(cartDTO.getProduct() != null ? product : existingCart.getProduct());
        existingCart.setQuantity(cartDTO.getStatus() != 0 ? cartDTO.getQuantity() : existingCart.getQuantity());
        existingCart.setPrice(cartDTO.getStatus() != 0 ? cartDTO.getPrice() : existingCart.getPrice());
        existingCart.setStatus(cartDTO.getStatus() != 0 ? cartDTO.getStatus() : existingCart.getStatus());

        return cartRepository.save(existingCart);
    }

    @Override
    public void deleteAllCart() {
        int id = authenticationService.getCurrentUserId();
        cartRepository.deleteAllByUserId(id);
    }

    @Override
    public void deleteCartByID(Integer id) {
        cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart", "Id", id));

        cartRepository.deleteById(id);
    }

}
