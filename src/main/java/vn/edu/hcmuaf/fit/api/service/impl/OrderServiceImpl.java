package vn.edu.hcmuaf.fit.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.*;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.repository.*;
import vn.edu.hcmuaf.fit.api.service.AuthenticationService;
import vn.edu.hcmuaf.fit.api.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order saveOrder(int addressId, OrderDTO orderDTO) {
        int userId = authenticationService.getCurrentUserId();

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        Address address = addressRepository.findById(addressId).orElseThrow(() ->
                new ResourceNotFoundException("Address", "Id", addressId));

        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setTotal(orderDTO.getTotal());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        order.setNote(orderDTO.getNote());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus((byte) 1);

        order.setOrderDetails(new ArrayList<>());

        for (OrderDetailDTO orderDetailDTO : orderDTO.getOrderDetails()) {
            Product product = productRepository.findById(orderDetailDTO.getProduct().getId()).orElseThrow(() ->
                    new ResourceNotFoundException("Product", "Id", orderDetailDTO.getProduct().getId()));

            // Check quantity in warehouse
            if (product.getQuantity() < orderDetailDTO.getQuantity()) {
                throw new IllegalArgumentException("Not enough stock for product ID: " + product.getId());
            }

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(orderDetailDTO.getQuantity());
            orderDetail.setPrice(orderDetailDTO.getPrice());
            orderDetail.setOrder(order);

            order.getOrderDetails().add(orderDetail);

            // Minus quantity after checkout
            product.setQuantity(product.getQuantity() - orderDetailDTO.getQuantity());
            productRepository.save(product);
        }

        return orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrderByUser() {
        int id = authenticationService.getCurrentUserId();
        List<Order> orders = orderRepository.findByUserId(id);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrderByStatus(int status) {
        List<Order> orders = orderRepository.findByStatus(status);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        UserDTO userDTO = null;
        AddressDTO addressDTO = null;
        User user = order.getUser();
        Address address = order.getAddress();

        if (user != null) {
            userDTO = new UserDTO(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhoneNumber()
            );
        }

        if (address != null) {
            addressDTO = new AddressDTO(
                    address.getId(),
                    address.getDetail(),
                    address.getWard(),
                    address.getCity(),
                    address.getProvince()
            );
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setUser(userDTO);
        orderDTO.setAddress(addressDTO);
        orderDTO.setTotal(order.getTotal());
        orderDTO.setPaymentMethod(order.getPaymentMethod());
        orderDTO.setNote(order.getNote());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setPaymentDate(order.getPaymentDate());
        orderDTO.setStatus(order.getStatus());

        return orderDTO;
    }

    @Override
    public OrderDTO getOrderByID(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order", "Id", id));
        return convertToDTO(order);
    }

    @Override
    public Order updateOrderByID(Integer id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order", "Id", id));

        existingOrder.setStatus(orderDTO.getStatus() != 0 ? orderDTO.getStatus() : existingOrder.getStatus());
        existingOrder.setPaymentDate(orderDTO.getPaymentDate() != null ? orderDTO.getPaymentDate() : existingOrder.getPaymentDate());

        return orderRepository.save(existingOrder);
    }

}
