package vn.edu.hcmuaf.fit.api.dto.order;

import lombok.Data;

import java.util.Optional;

@Data
public class CreateOrderRequest {
    int idAddress;
    double totalAmount;
    String paymentMethod;
    String paymentDate;
    int productID;
}
