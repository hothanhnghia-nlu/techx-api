package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.model.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(int addressId, OrderDTO orderDTO);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrderByUser();
    OrderDTO getOrderByID(Integer id);
    Order updateOrderByID(Integer id, OrderDTO orderDTO);

}
