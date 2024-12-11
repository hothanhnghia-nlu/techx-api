package vn.edu.hcmuaf.fit.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.*;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.repository.OrderDetailRepository;
import vn.edu.hcmuaf.fit.api.service.OrderDetailService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailDTO> getOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        return orderDetails.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDetailDTO convertToDTO(OrderDetail orderDetail) {
        OrderDTO orderDTO;
        UserDTO userDTO = null;
        AddressDTO addressDTO = null;
        ProductDTO productDTO = null;
        ImageDTO imageDTO;
        Order order = orderDetail.getOrder();
        User user = order.getUser();
        Address address = order.getAddress();
        Product product = orderDetail.getProduct();
        List<ImageDTO> imageDTOList = new ArrayList<>();

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
        orderDTO = new OrderDTO(
                order.getId(),
                order.getTotal(),
                order.getPaymentMethod(),
                order.getNote(),
                order.getOrderDate(),
                order.getPaymentDate()
        );

        if (product != null) {
            if (product.getImages() != null) {
                for (Image image : product.getImages()) {
                    imageDTO = new ImageDTO(
                            image.getId(),
                            image.getName(),
                            image.getUrl()
                    );
                    imageDTOList.add(imageDTO);
                }
            }
            productDTO = new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getOriginalPrice(),
                    product.getNewPrice(),
                    product.getColor(),
                    product.getRam(),
                    product.getStorage()
            );
        }

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(orderDetail.getId());
        orderDetailDTO.setOrder(orderDTO);
        orderDetailDTO.getOrder().setUser(userDTO);
        orderDetailDTO.getOrder().setAddress(addressDTO);
        orderDetailDTO.setProduct(productDTO);
        orderDetailDTO.setQuantity(orderDetail.getQuantity());
        orderDetailDTO.setPrice(orderDetail.getPrice());

        return orderDetailDTO;
    }

    @Override
    public OrderDetailDTO getOrderDetailByOrder(int orderId) {
        OrderDetail orderDetail = orderDetailRepository.findByOrderId(orderId);
        return convertToDTO(orderDetail);
    }

    @Override
    public OrderDetailDTO getOrderDetailByID(Integer id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("OrderDetail", "Id", id));
        return convertToDTO(orderDetail);
    }

}
