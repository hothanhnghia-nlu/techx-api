package vn.edu.hcmuaf.fit.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmuaf.fit.api.dto.OrderDTO;
import vn.edu.hcmuaf.fit.api.service.StatisticalService;

import java.util.List;

@RestController
@RequestMapping("api/v1/statistical")
@Tag(name = "Statistical Controller")
public class StatisticalController {
    @Autowired
    private StatisticalService statisticalService;

    public StatisticalController(StatisticalService statisticalService) {
        this.statisticalService = statisticalService;
    }

    // Get total customers
    @GetMapping("/total-customers")
    public ResponseEntity<Integer> getTotalCustomers() {
        return new ResponseEntity<>(statisticalService.totalCustomers(), HttpStatus.OK);
    }

    // Get total orders
    @GetMapping("/total-orders")
    public ResponseEntity<Integer> getTotalOrders() {
        return new ResponseEntity<>(statisticalService.totalOrders(), HttpStatus.OK);
    }

    // Get total revenue
    @GetMapping("/total-revenue")
    public ResponseEntity<Double> getTotalRevenues() {
        return new ResponseEntity<>(statisticalService.totalRevenue(), HttpStatus.OK);
    }

    // Get all Order
    @GetMapping(path = "/recent-orders")
    public List<OrderDTO> getAllOrders() {
        return statisticalService.recentOrders();
    }

}
