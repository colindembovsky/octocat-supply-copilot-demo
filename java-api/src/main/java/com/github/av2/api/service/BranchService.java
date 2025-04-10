package com.github.av2.api.service;

import com.github.av2.api.model.Branch;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {
    private final List<Branch> branches = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with seed data
        branches.add(new Branch(
            1, 1, "Downtown Branch", 
            "Main downtown location", 
            "456 Market St", 
            "Sarah Wilson", 
            "swilson@octo.com", 
            "555-0201"
        ));
        branches.add(new Branch(
            2, 1, "Westside Branch", 
            "Western district branch", 
            "789 West Ave", 
            "Robert Brown", 
            "rbrown@octo.com", 
            "555-0202"
        ));
    }

    public List<Branch> findAll() {
        return new ArrayList<>(branches);
    }

    public Optional<Branch> findById(Integer id) {
        return branches.stream()
            .filter(b -> b.getBranchId().equals(id))
            .findFirst();
    }

    public Branch save(Branch branch) {
        branches.removeIf(b -> b.getBranchId().equals(branch.getBranchId()));
        branches.add(branch);
        return branch;
    }

    public boolean deleteById(Integer id) {
        return branches.removeIf(b -> b.getBranchId().equals(id));
    }
}