package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.*;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.repository.CartRepository;
import vn.edu.hcmuaf.fit.api.repository.ProductRepository;
import vn.edu.hcmuaf.fit.api.repository.UserRepository;
import vn.edu.hcmuaf.fit.api.service.CartService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart saveCart(int userId, int productId, CartDTO cartDTO) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", cartDTO.getId()));

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", cartDTO.getId()));

        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(cartDTO.getQuantity());
        cart.setPrice(cartDTO.getPrice());
        cart.setStatus((byte) 1);
        cart.setOrderDate(LocalDateTime.now());

        return cartRepository.save(cart);
    }

    @Override
    public List<CartDTO> getCarts() {
        List<Cart> carts = cartRepository.findAll();

        return carts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private CartDTO convertToDTO(Cart cart) {
        UserDTO userDTO = null;
        ProductDTO productDTO = null;
        ImageDTO imageDTO;
        User user = cart.getUser();
        Product product = cart.getProduct();
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

        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUser(userDTO);
        cartDTO.setProduct(productDTO);
        cartDTO.setQuantity(cart.getQuantity());
        cartDTO.setPrice(cart.getPrice());
        cartDTO.setStatus(cart.getStatus());
        cartDTO.setOrderDate(cart.getOrderDate());

        return cartDTO;
    }

    @Override
    public Cart getCartByID(Integer id) {
        return cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart", "Id", id));
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
    public void deleteCartByID(Integer id) {
        cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart", "Id", id));

        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));

        productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));

        cartRepository.deleteById(id);
    }
}
