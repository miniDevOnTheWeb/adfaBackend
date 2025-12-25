package com.adfa.adfa.web.controller;

import com.adfa.adfa.model.dto.RefereeRequest;
import com.adfa.adfa.model.entity.Referee;
import com.adfa.adfa.service.RefereeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/referees")
public class RefereeController {
    private final RefereeService refereeService;

    public RefereeController(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllReferees() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "referees", refereeService.getAllReferees()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRefereeById(@PathVariable UUID id) {
        Referee referee = refereeService.getRefereeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "referee", referee
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRefereeById(@PathVariable UUID id) {
        refereeService.deleteRefereeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Referee eliminado."
        ));
    }

    @PostMapping
    public ResponseEntity<?> createReferee(@RequestBody RefereeRequest request) {
        Referee createdReferee = refereeService.createReferee(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "referee", createdReferee,
                "message", "Referee creado exitosamente"
        ));
    }
}
