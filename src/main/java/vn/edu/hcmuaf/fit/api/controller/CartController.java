package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.CartDTO;
import vn.edu.hcmuaf.fit.api.model.Cart;
import vn.edu.hcmuaf.fit.api.service.CartService;

import java.util.List;

@RestController
@RequestMapping("api/v1/carts")
@Tag(name = "Cart Controller")
public class CartController {
    @Autowired
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Create a new Cart
    @PostMapping()
    public ResponseEntity<Cart> createCart(@RequestParam int productId) {
        return new ResponseEntity<>(cartService.saveCart(productId), HttpStatus.CREATED);
    }

    // Get all Cart
    @GetMapping(path = "/getAllCarts")
    public List<CartDTO> getAllCarts() {
        return cartService.getAllCarts();
    }

    // Get Cart by user
    @GetMapping(path = "/getCartsByUser")
    public ResponseEntity<List<CartDTO>> getCartsByUser() {
        return new ResponseEntity<>(cartService.getCartByUser(), HttpStatus.OK);
    }

    // Get Quantity in Cart
    @GetMapping(path = "/quantity")
    public ResponseEntity<Integer> getCartQuantity() {
        return new ResponseEntity<>(cartService.getQuantityInCart(), HttpStatus.OK);
    }

    // Get Cart by id
    @GetMapping("{id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("id") int id) {
        return new ResponseEntity<>(cartService.getCartByID(id), HttpStatus.OK);
    }

    // Update Cart by id
    @PutMapping("{id}")
    public ResponseEntity<Cart> updateCartById(@PathVariable("id") int id,
                                               @RequestBody CartDTO cartDTO) {
        return new ResponseEntity<>(cartService.updateCartByID(id, cartDTO), HttpStatus.OK);
    }

    // Delete all Cart
    @DeleteMapping()
    public ResponseEntity<String> deleteCart() {
        cartService.deleteAllCart();
        return new ResponseEntity<>("All of cart is deleted successfully!", HttpStatus.OK);
    }

    // Delete Cart by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCartById(@PathVariable("id") int id) {
        cartService.deleteCartByID(id);
        return new ResponseEntity<>("Cart " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
