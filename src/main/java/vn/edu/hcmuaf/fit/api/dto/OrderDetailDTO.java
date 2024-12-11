package vn.edu.hcmuaf.fit.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO {
    private Integer id;
    private OrderDTO order;
    private ProductDTO product;
    private int quantity;
    private double price;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Integer id, ProductDTO product, int quantity, double price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
}
