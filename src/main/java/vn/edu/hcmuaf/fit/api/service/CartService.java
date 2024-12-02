package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.CartDTO;
import vn.edu.hcmuaf.fit.api.model.Cart;

import java.util.List;

public interface CartService {
    Cart saveCart(int userId, int productId, CartDTO cartDTO);
    List<CartDTO> getAllCarts();
    List<CartDTO> getCartByUser();
    Cart getCartByID(Integer id);
    int getQuantityInCart();
    Cart updateCartByID(Integer id, CartDTO cartDTO);
    void deleteAllCart();
    void deleteCartByID(Integer id);
}
