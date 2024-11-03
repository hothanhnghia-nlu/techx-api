package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.model.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(OrderDTO orderDTO);
    List<Order> getOrders();
    Order getOrderByID(Integer id);
    Order updateOrderByID(Integer id, OrderDTO orderDTO);
    void deleteOrderByID(Integer id);

}
