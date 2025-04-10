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

    private final SeedData seedData;

    @Autowired
    public HeadquartersService(SeedData seedData) {
        this.seedData = seedData;
        this.orders.headquarters(seedData.getHeadquarters());
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