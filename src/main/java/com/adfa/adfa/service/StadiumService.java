package com.adfa.adfa.service;

import com.adfa.adfa.data.repository.StadiumRepository;
import com.adfa.adfa.model.dto.StadiumRequest;
import com.adfa.adfa.model.entity.Stadium;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StadiumService {
    private final StadiumRepository stadiumRepository;

    public StadiumService(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    public Stadium createStadium (StadiumRequest request) {
        Stadium stadium = new Stadium();
        stadium.setName(request.getName());
        stadium.setLocation(request.getLocation());

        return stadiumRepository.save(stadium);
    }

    public void deleteStadium (UUID id) {
        stadiumRepository.deleteById(id);
    }

    public Stadium getStadiumById (UUID id) {
        return stadiumRepository.findById(id).orElseThrow(() -> new RuntimeException("Estadio no encontrado"));
    }

    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }
}
