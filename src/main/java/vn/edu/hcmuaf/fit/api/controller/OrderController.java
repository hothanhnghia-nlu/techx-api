package vn.edu.hcmuaf.fit.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.model.Order;
import vn.edu.hcmuaf.fit.api.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new Order
    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO order) {
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    // Get all Order
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    // Get Order by id
    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(orderService.getOrderByID(id), HttpStatus.OK);
    }

    // Update Order by id
    @PutMapping("{id}")
    public ResponseEntity<Order> updateOrderById(@PathVariable ("id") int id,
                                                       @RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.updateOrderByID(id, orderDTO), HttpStatus.OK);
    }

    // Delete Order by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable ("id") int id) {
        orderService.deleteOrderByID(id);
        return new ResponseEntity<>("Order " + id + " is deleted successfully!", HttpStatus.OK);
    }

}
