package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.dto.OrderDetailDTO;
import vn.edu.hcmuaf.fit.api.dto.order.CreateOrderRequest;
import vn.edu.hcmuaf.fit.api.model.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(CreateOrderRequest createOrderRequest);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrderByUser();
    List<OrderDTO> getOrderByStatus(int status);
    OrderDTO getOrderByID(Integer id);
    Order updateOrderByID(Integer id, OrderDTO orderDTO);

}
