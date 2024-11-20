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
}
