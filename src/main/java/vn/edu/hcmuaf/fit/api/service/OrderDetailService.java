package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getOrderDetails();
    OrderDetail getOrderDetailByID(Integer id);
}
