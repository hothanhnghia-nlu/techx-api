package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.OrderDetailDTO;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDTO> getOrderDetails();
    OrderDetailDTO getOrderDetailByOrder(int orderId);
    OrderDetailDTO getOrderDetailByStatus(int status);
    OrderDetailDTO getOrderDetailByID(Integer id);
}
