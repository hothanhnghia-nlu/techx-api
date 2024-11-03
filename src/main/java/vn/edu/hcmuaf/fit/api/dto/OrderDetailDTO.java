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
}
