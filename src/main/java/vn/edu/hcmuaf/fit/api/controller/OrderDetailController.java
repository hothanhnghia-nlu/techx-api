package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.model.OrderDetail;
import vn.edu.hcmuaf.fit.api.service.OrderDetailService;

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
    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailService.getOrderDetails();
    }

    // Get OrderDetail by id
    @GetMapping("{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(orderDetailService.getOrderDetailByID(id), HttpStatus.OK);
    }

}
