package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.OrderDetailDTO;
import vn.edu.hcmuaf.fit.api.model.*;
import vn.edu.hcmuaf.fit.api.service.OrderDetailService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/order-details")
@Tag(name = "Order detail Controller")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    // Get all OrderDetail
    @GetMapping
    public List<OrderDetailDTO> getAllOrderDetails() {
        return orderDetailService.getOrderDetails();
    }

    // Get OrderDetail by order id
    @GetMapping("/by-order")
    public ResponseEntity<OrderDetailDTO> getOrderDetailByOrderId(@RequestParam("orderId") int orderId) {
        return new ResponseEntity<>(orderDetailService.getOrderDetailByOrder(orderId), HttpStatus.OK);
    }

    // Get OrderDetail by id
    @GetMapping("{id}")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(orderDetailService.getOrderDetailByID(id), HttpStatus.OK);
    }

}
