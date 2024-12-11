package vn.edu.hcmuaf.fit.api.service;

import vn.edu.hcmuaf.fit.api.dto.OrderDTO;

import java.util.List;

public interface StatisticalService {
    int totalCustomers();
    int totalOrders();
    double totalRevenue();
    List<OrderDTO> recentOrders();
}
