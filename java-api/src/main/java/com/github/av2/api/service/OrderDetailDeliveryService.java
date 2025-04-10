package com.github.av2.api.service;

import com.github.av2.api.model.OrderDetailDelivery;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailDeliveryService {
    private final List<OrderDetailDelivery> orderDetailDeliveries = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with seed data
        orderDetailDeliveries.add(new OrderDetailDelivery(
            1, 1, 3, "scheduled", "2024-04-15", "First batch"
        ));
        orderDetailDeliveries.add(new OrderDetailDelivery(
            2, 1, 2, "in-transit", "2024-04-16", "Second batch"
        ));
    }

    public List<OrderDetailDelivery> findAll() {
        return new ArrayList<>(orderDetailDeliveries);
    }

    public Optional<OrderDetailDelivery> findById(Integer id) {
        return orderDetailDeliveries.stream()
            .filter(odd -> odd.getDeliveryId().equals(id))
            .findFirst();
    }

    public OrderDetailDelivery save(OrderDetailDelivery orderDetailDelivery) {
        orderDetailDeliveries.removeIf(odd -> odd.getDeliveryId().equals(orderDetailDelivery.getDeliveryId()));
        orderDetailDeliveries.add(orderDetailDelivery);
        return orderDetailDelivery;
    }

    public boolean deleteById(Integer id) {
        return orderDetailDeliveries.removeIf(odd -> odd.getDeliveryId().equals(id));
    }
}