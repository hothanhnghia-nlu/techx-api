package vn.edu.hcmuaf.fit.api.controller;

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

    // Get OrderDetail by status
    @GetMapping("/by-status")
    public ResponseEntity<OrderDetailDTO> getOrderDetailByStatus(@RequestParam("status") int status) {
        return new ResponseEntity<>(orderDetailService.getOrderDetailByStatus(status), HttpStatus.OK);
    }

    // Get OrderDetail by id
    @GetMapping("{id}")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(orderDetailService.getOrderDetailByID(id), HttpStatus.OK);
    }

}
