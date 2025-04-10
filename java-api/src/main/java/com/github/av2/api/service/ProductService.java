package com.github.av2.api.service;

import com.github.av2.api.model.Product;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with seed data
        products.add(new Product(
            1, 1, "PowerTool Pro X1", "High-performance powertool",
            1299.99f, "PWR-001", "piece", "powertool.png"
        ));
        products.add(new Product(
            2, 1, "Webcam Pro", "Ergonomic webcam",
            49.99f, "WEB-001", "piece", "webcam.png"
        ));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(Integer id) {
        return products.stream()
            .filter(p -> p.getProductId().equals(id))
            .findFirst();
    }

    public Product save(Product product) {
        products.removeIf(p -> p.getProductId().equals(product.getProductId()));
        products.add(product);
        return product;
    }

    public boolean deleteById(Integer id) {
        return products.removeIf(p -> p.getProductId().equals(id));
    }
}