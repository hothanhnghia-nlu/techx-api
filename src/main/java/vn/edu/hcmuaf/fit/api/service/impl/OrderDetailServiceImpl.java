package vn.edu.hcmuaf.fit.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.api.dto.OrderDetailDTO;
import vn.edu.hcmuaf.fit.api.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.api.model.OrderDetail;
import vn.edu.hcmuaf.fit.api.repository.OrderDetailRepository;
import vn.edu.hcmuaf.fit.api.service.OrderDetailService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailDTO.getId());
//        OrderDetail.setName(OrderDetailDTO.getName());

        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public List<OrderDetail> getOrderDetails() {
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail getOrderDetailByID(Integer id) {
        return orderDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("OrderDetail", "Id", id));
    }

    @Override
    public OrderDetail updateOrderDetailByID(Integer id, OrderDetailDTO orderDetailDTO) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("OrderDetail", "Id", id));

//        existingOrderDetail.setName(orderDetailDTO.getName() != null ? orderDetailDTO.getName() : existingOrderDetail.getName());

        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void deleteOrderDetailByID(Integer id) {
        orderDetailRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("OrderDetail", "Id", id));

        orderDetailRepository.deleteById(id);
    }
}
