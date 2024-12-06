package vn.edu.hcmuaf.fit.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CartDTO {
    private Integer id;
    private UserDTO user;
    private ProductDTO product;
    private int quantity;
    private double price;
    private byte status;
    private LocalDateTime orderDate;

    public CartDTO() {
    }

    public CartDTO(Integer id, UserDTO user, ProductDTO product, int quantity, double price, byte status, LocalDateTime orderDate) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.orderDate = orderDate;
    }
}
