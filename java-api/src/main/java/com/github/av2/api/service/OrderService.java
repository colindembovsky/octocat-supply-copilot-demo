package com.github.av2.api.service;

import com.github.av2.api.model.Order;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with seed data
        orders.add(new Order(
            1, 1, "2024-04-10", "pending", "Standard order"
        ));
        orders.add(new Order(
            2, 2, "2024-04-11", "processing", "Rush order"
        ));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> findById(Integer id) {
        return orders.stream()
            .filter(o -> o.getOrderId().equals(id))
            .findFirst();
    }

    public Order save(Order order) {
        orders.removeIf(o -> o.getOrderId().equals(order.getOrderId()));
        orders.add(order);
        return order;
    }

    public boolean deleteById(Integer id) {
        return orders.removeIf(o -> o.getOrderId().equals(id));
    }
}