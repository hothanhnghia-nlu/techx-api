package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.dto.OrderDetailDTO;
import vn.edu.hcmuaf.fit.api.model.Order;
import vn.edu.hcmuaf.fit.api.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@Tag(name = "Order Controller")
public class OrderController {
    @Autowired
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new Order
    @PostMapping()
    public ResponseEntity<Order> createOrder(@RequestParam int addressId,
                                             @ModelAttribute OrderDTO order) {
        return new ResponseEntity<>(orderService.saveOrder(addressId, order), HttpStatus.CREATED);
    }

    // Get all Order
    @GetMapping(path = "/all-orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get Order by user
    @GetMapping(path = "/by-user")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser() {
        return new ResponseEntity<>(orderService.getOrderByUser(), HttpStatus.OK);
    }

    // Get Order by status
    @GetMapping("/by-status")
    public ResponseEntity<List<OrderDTO>> getOrderByStatus(@RequestParam("status") int status) {
        return new ResponseEntity<>(orderService.getOrderByStatus(status), HttpStatus.OK);
    }

    // Get Order by id
    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable ("id") int id) {
        return new ResponseEntity<>(orderService.getOrderByID(id), HttpStatus.OK);
    }

    // Update Order by id
    @PutMapping("{id}")
    public ResponseEntity<Order> updateOrderById(@PathVariable ("id") int id,
                                                 @ModelAttribute OrderDTO orderDTO) {
        return new ResponseEntity<>(orderService.updateOrderByID(id, orderDTO), HttpStatus.OK);
    }

}
