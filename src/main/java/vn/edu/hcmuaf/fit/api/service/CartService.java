package vn.edu.hcmuaf.fit.api.service;

import org.springframework.http.ResponseEntity;
import vn.edu.hcmuaf.fit.api.dto.CartDTO;
import vn.edu.hcmuaf.fit.api.model.Cart;
import vn.edu.hcmuaf.fit.api.model.Product;

import java.util.List;

public interface CartService {
    ResponseEntity<Cart> saveCart(int productId);
    List<CartDTO> getAllCarts();
    List<CartDTO> getCartByUser();
    Cart getCartByID(Integer id);
    int getQuantityInCart();
    Cart updateCartByID(Integer id, CartDTO cartDTO);
    Cart updateCartByID(Cart cart, Product product);
    void deleteAllCart();
    void deleteCartByID(Integer id);
}
