package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.*;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.repository.OrderRepository;
import vn.edu.hcmuaf.fit.api.repository.UserRepository;
import vn.edu.hcmuaf.fit.api.service.StatisticalService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticalServiceImpl implements StatisticalService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public int totalCustomers() {
        List<User> users = userRepository.findAll().stream()
                .filter(u -> u.getRole() != 2)
                .toList();

        return users.size();
    }

    @Override
    public int totalOrders() {
        List<Order> orders = orderRepository.findAll().stream()
                .filter(o -> o.getOrderDate().getYear() == LocalDateTime.now().getYear())
                .toList();

        return orders.size();
    }

    @Override
    public double totalRevenue() {
        double total = 0;
        List<Order> orders = orderRepository.findAll();
        for (Order o : orders) {
            total += o.getTotal();
        }
        return total;
    }

    @Override
    public List<OrderDTO> recentOrders() {
        List<Order> orders = orderRepository.findAll();
        Collections.reverse(orders);
        return orders
                .subList(0, Math.min(5, orders.size()))
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Order order) {
        UserDTO userDTO = null;
        AddressDTO addressDTO = null;
        User user = order.getUser();
        Address address = order.getAddress();

        // Convert User and Address to DTO
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

        // Map OrderDetails to OrderDetailDTO
        List<OrderDetailDTO> orderDetailDTOs = order.getOrderDetails().stream().map(detail -> {
            ProductDTO productDTO = convertToProductDTO(detail.getProduct());
            return new OrderDetailDTO(
                    detail.getId(),
                    productDTO,
                    detail.getQuantity(),
                    detail.getPrice()
            );
        }).collect(Collectors.toList());

        // Map Order to OrderDTO
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
        orderDTO.setOrderDetails(orderDetailDTOs);

        return orderDTO;
    }

    private ProductDTO convertToProductDTO(Product product) {
        List<ImageDTO> imageDTOs = product.getImages().stream().map(image -> new ImageDTO(
                image.getId(),
                image.getName(),
                image.getUrl()
        )).collect(Collectors.toList());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setOriginalPrice(product.getOriginalPrice());
        productDTO.setNewPrice(product.getNewPrice());
        productDTO.setColor(product.getColor());
        productDTO.setRam(product.getRam());
        productDTO.setStorage(product.getStorage());
        productDTO.setImages(imageDTOs);

        return productDTO;
    }

}
