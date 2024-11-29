package vn.edu.hcmuaf.fit.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {
    private Integer id;
    private UserDTO user;
    private AddressDTO address;
    private double total;
    private String paymentMethod;
    private String note;
    private LocalDateTime orderDate;
    private LocalDateTime paymentDate;
    private byte status;
    private List<OrderDetailDTO> orderDetails;
}
