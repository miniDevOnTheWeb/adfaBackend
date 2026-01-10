package com.adfa.adfa.web.controller;

import com.adfa.adfa.model.dto.MatchRequest;
import com.adfa.adfa.model.dto.ScoreRequest;
import com.adfa.adfa.model.entity.Match;
import com.adfa.adfa.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/matches")
public class MatchController {
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<?> getAllMatches() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "matches", matchService.getAllMatches()
        ));
    }

    @PostMapping
    public ResponseEntity<?> createMatches(@Valid @RequestBody MatchRequest request) {
        Match match = matchService.createMatch(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Partido programado exitosamente."
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMatch(@PathVariable UUID id) {
        matchService.deleteMatchById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Partido eliminado."
        ));
    }

    @PutMapping("/setScore")
    public ResponseEntity<?> setScore(@Valid @RequestBody ScoreRequest request) {
        Match match = matchService.setScore(request);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Marcador actualizado exitosamente.",
                "match", match
        ));
    }
}
