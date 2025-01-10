package vn.edu.hcmuaf.fit.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.fit.api.dto.*;
import vn.edu.hcmuaf.fit.api.dto.order.CreateOrderRequest;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.repository.*;
import vn.edu.hcmuaf.fit.api.service.AuthenticationService;
import vn.edu.hcmuaf.fit.api.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    @Override
    public Order saveOrder(CreateOrderRequest createOrderRequest) {
        System.out.println(createOrderRequest.toString());
        int userId = authenticationService.getCurrentUserId();

        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", userId));

        Address address = addressRepository.findById(createOrderRequest.getIdAddress()).orElseThrow(() ->
                new ResourceNotFoundException("Address", "Id", createOrderRequest.getIdAddress()));

        List<Cart> carts = cartRepository.findByUserId(userId);
        Order order = new Order();
        order.setUser(user);
        order.setAddress(address);
        order.setTotal(createOrderRequest.getTotalAmount());
        order.setPaymentMethod(createOrderRequest.getPaymentMethod());
        order.setNote("");
        order.setOrderDate(LocalDateTime.now());
        order.setStatus((byte) 4);
        orderRepository.save(order);
        if (createOrderRequest.getProductID() != 0) {
            Product product = productRepository.findById(createOrderRequest.getProductID()).orElseThrow(() ->
                    new ResourceNotFoundException("Product", "Id", createOrderRequest.getProductID()));
            if (product.getQuantity() < 1) {
                throw new IllegalArgumentException("Not enough stock for product ID: " + product.getId());
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(1);
            orderDetail.setPrice(product.getNewPrice());
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);

            // Minus quantity after checkout
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
            cartRepository.deleteAllByUserId(userId);

            return orderRepository.save(order);
        }
        for (Cart cart : carts) {
            Product product = productRepository.findById(cart.getId()).orElseThrow(() ->
                    new ResourceNotFoundException("Product", "Id", cart.getId()));

            // Check quantity in warehouse
            if (product.getQuantity() < cart.getQuantity()) {
                throw new IllegalArgumentException("Not enough stock for product ID: " + product.getId());
            }

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setPrice(cart.getPrice());
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);

            // Minus quantity after checkout
            product.setQuantity(product.getQuantity() - cart.getQuantity());
            productRepository.save(product);
            cartRepository.deleteAllByUserId(userId);
        }

        return orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        Collections.reverse(orders);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrderByUser() {
        int id = authenticationService.getCurrentUserId();
        List<Order> orders = orderRepository.findByUserId(id);
        Collections.reverse(orders);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrderByStatus(int status) {
        List<Order> orders = orderRepository.findByStatus(status);
        Collections.reverse(orders);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
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
