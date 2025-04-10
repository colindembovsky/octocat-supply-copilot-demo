package com.github.av2.api.service;

import com.github.av2.api.model.Supplier;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final List<Supplier> suppliers = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with seed data
        suppliers.add(new Supplier(
            1, "CircuitCore Labs", "Leading technology supplier",
            "John Smith", "john@circuitcorelabs.co", "555-0101"
        ));
        suppliers.add(new Supplier(
            2, "ConnectSphere", "Advanced tech products supplier",
            "Jane Doe", "jane@connectsphere.com", "555-0102"
        ));
    }

    public List<Supplier> findAll() {
        return new ArrayList<>(suppliers);
    }

    public Optional<Supplier> findById(Integer id) {
        return suppliers.stream()
            .filter(s -> s.getSupplierId().equals(id))
            .findFirst();
    }

    public Supplier save(Supplier supplier) {
        suppliers.removeIf(s -> s.getSupplierId().equals(supplier.getSupplierId()));
        suppliers.add(supplier);
        return supplier;
    }

    public boolean deleteById(Integer id) {
        return suppliers.removeIf(s -> s.getSupplierId().equals(id));
    }
}