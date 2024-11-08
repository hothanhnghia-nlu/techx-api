package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.CartDTO;
import vn.edu.hcmuaf.fit.api.model.Cart;

import java.util.List;

public interface CartService {
    Cart saveCart(CartDTO cartDTO);
    List<Cart> getCarts();
    Cart getCartByID(Integer id);
    Cart updateCartByID(Integer id, CartDTO cartDTO);
    void deleteCartByID(Integer id);

}
