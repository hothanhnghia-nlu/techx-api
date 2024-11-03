package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.OrderDetailDTO;
import vn.edu.hcmuaf.fit.api.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail saveOrderDetail(OrderDetailDTO orderDetailDTO);
    List<OrderDetail> getOrderDetails();
    OrderDetail getOrderDetailByID(Integer id);
    OrderDetail updateOrderDetailByID(Integer id, OrderDetailDTO orderDetailDTO);
    void deleteOrderDetailByID(Integer id);

}
