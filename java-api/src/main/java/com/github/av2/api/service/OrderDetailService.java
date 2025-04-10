package com.github.av2.api.service;

import com.github.av2.api.model.OrderDetail;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {
    private final List<OrderDetail> orderDetails = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with seed data
        orderDetails.add(new OrderDetail(
            1, 1, 1, 5, 1299.99f, "Bulk order"
        ));
        orderDetails.add(new OrderDetail(
            2, 1, 2, 10, 49.99f, "Standard order"
        ));
    }

    public List<OrderDetail> findAll() {
        return new ArrayList<>(orderDetails);
    }

    public Optional<OrderDetail> findById(Integer id) {
        return orderDetails.stream()
            .filter(od -> od.getOrderDetailId().equals(id))
            .findFirst();
    }

    public OrderDetail save(OrderDetail orderDetail) {
        orderDetails.removeIf(od -> od.getOrderDetailId().equals(orderDetail.getOrderDetailId()));
        orderDetails.add(orderDetail);
        return orderDetail;
    }

    public boolean deleteById(Integer id) {
        return orderDetails.removeIf(od -> od.getOrderDetailId().equals(id));
    }
}