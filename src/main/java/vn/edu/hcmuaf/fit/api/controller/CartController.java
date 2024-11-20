package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.CartDTO;
import vn.edu.hcmuaf.fit.api.model.Cart;
import vn.edu.hcmuaf.fit.api.service.CartService;

import java.util.List;

@RestController
@RequestMapping("api/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Create a new Cart
    @PostMapping()
    public ResponseEntity<Cart> createCart(@RequestParam int userId,
                                           @RequestParam int productId,
                                           @RequestBody CartDTO cart) {
        return new ResponseEntity<>(cartService.saveCart(userId, productId, cart), HttpStatus.CREATED);
    }

    // Get all Cart
    @GetMapping
    public List<CartDTO> getAllCarts() {
        return cartService.getCarts();
    }

    // Get Cart by id
    @GetMapping("{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(cartService.getCartByID(id), HttpStatus.OK);
    }

    // Update Cart by id
    @PutMapping("{id}")
    public ResponseEntity<Cart> updateCartById(@PathVariable ("id") int id,
                                                       @RequestBody CartDTO cartDTO) {
        return new ResponseEntity<>(cartService.updateCartByID(id, cartDTO), HttpStatus.OK);
    }

    // Delete Cart by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCartById(@PathVariable ("id") int id) {
        cartService.deleteCartByID(id);
        return new ResponseEntity<>("Cart " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
