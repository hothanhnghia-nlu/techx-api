package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.OrderDetailDTO;
import vn.edu.hcmuaf.fit.api.model.OrderDetail;
import vn.edu.hcmuaf.fit.api.service.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("api/order-details")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    // Create a new OrderDetail
    @PostMapping()
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetailDTO orderDetail) {
        return new ResponseEntity<>(orderDetailService.saveOrderDetail(orderDetail), HttpStatus.CREATED);
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

    // Update OrderDetail by id
    @PutMapping("{id}")
    public ResponseEntity<OrderDetail> updateOrderDetailById(@PathVariable ("id") int id,
                                                       @RequestBody OrderDetailDTO orderDetailDTO) {
        return new ResponseEntity<>(orderDetailService.updateOrderDetailByID(id, orderDetailDTO), HttpStatus.OK);
    }

    // Delete OrderDetail by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderDetailById(@PathVariable ("id") int id) {
        orderDetailService.deleteOrderDetailByID(id);
        return new ResponseEntity<>("OrderDetail " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
