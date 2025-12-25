package com.adfa.adfa.web.controller;

import com.adfa.adfa.model.dto.StadiumRequest;
import com.adfa.adfa.model.entity.Stadium;
import com.adfa.adfa.service.StadiumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/stadiums")
public class StadiumController {
    private final StadiumService stadiumService;

    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    @GetMapping
    public ResponseEntity<?> getAllStadiums() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "stadiums", stadiumService.getAllStadiums()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllStadiums(@PathVariable UUID id) {
        Stadium stadium = stadiumService.getStadiumById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "stadiums", stadium
        ));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStadium(@RequestBody StadiumRequest request) {
        Stadium createdStadium = stadiumService.createStadium(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "stadium", createdStadium,
                "message", "Estadio creado exitosamente"
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStadium(@PathVariable UUID id) {
        stadiumService.deleteStadium(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Estadio eliminado."
        ));
    }
}
