package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.Order;
import vn.edu.hcmuaf.fit.api.repository.OrderRepository;
import vn.edu.hcmuaf.fit.api.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
//        order.setName(orderDTO.getName());
        order.setStatus((byte) 1);
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderByID(Integer id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order", "Id", id));
    }

    @Override
    public Order updateOrderByID(Integer id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order", "Id", id));

//        existingOrder.setName(orderDTO.getName() != null ? orderDTO.getName() : existingOrder.getName());
        existingOrder.setStatus(orderDTO.getStatus() != 0 ? orderDTO.getStatus() : existingOrder.getStatus());
        existingOrder.setPaymentDate(LocalDateTime.now());

        return orderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrderByID(Integer id) {
        orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order", "Id", id));

        orderRepository.deleteById(id);
    }
}
