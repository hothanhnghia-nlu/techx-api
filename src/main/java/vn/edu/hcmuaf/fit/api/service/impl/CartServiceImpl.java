package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.CartDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Cart;
import vn.edu.hcmuaf.fit.api.repository.CartRepository;
import vn.edu.hcmuaf.fit.api.service.CartService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository CartRepository) {
        this.cartRepository = CartRepository;
    }

    @Override
    public Cart saveCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
//        Cart.setName(CartDTO.getName());
        cart.setStatus((byte) 1);
        cart.setCreatedAt(LocalDateTime.now());

        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCarts() {
        return cartRepository.findAll();
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

//        existingCart.setName(CartDTO.getName() != null ? CartDTO.getName() : existingCart.getName());
        existingCart.setStatus(cartDTO.getStatus() != 0 ? cartDTO.getStatus() : existingCart.getStatus());
        existingCart.setCreatedAt(LocalDateTime.now());

        return cartRepository.save(existingCart);
    }

    @Override
    public void deleteCartByID(Integer id) {
        cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart", "Id", id));

        cartRepository.deleteById(id);
    }
}
