package com.github.av2.api.service;

import com.github.av2.api.model.Headquarters;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeadquartersService {
    private final List<Headquarters> headquartersList = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize with seed data
        headquartersList.add(new Headquarters(
            1,
            "Main Office",
            "Corporate headquarters",
            "123 Main St, Business District",
            "Michael Johnson",
            "mjohnson@octo.com",
            "555-0001"
        ));
    }

    public List<Headquarters> findAll() {
        return new ArrayList<>(headquartersList);
    }

    public Optional<Headquarters> findById(Integer id) {
        return headquartersList.stream()
            .filter(h -> h.getHeadquartersId().equals(id))
            .findFirst();
    }

    public Headquarters save(Headquarters headquarters) {
        headquartersList.removeIf(h -> h.getHeadquartersId().equals(headquarters.getHeadquartersId()));
        headquartersList.add(headquarters);
        return headquarters;
    }

    public boolean deleteById(Integer id) {
        return headquartersList.removeIf(h -> h.getHeadquartersId().equals(id));
    }
}