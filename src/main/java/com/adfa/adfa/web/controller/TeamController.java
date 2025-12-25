package com.adfa.adfa.web.controller;

import com.adfa.adfa.model.dto.TeamRequest;
import com.adfa.adfa.model.entity.Team;
import com.adfa.adfa.service.TeamService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTeams() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "teams", teamService.getAllTeams()
        ));
    }

    @PostMapping
    public ResponseEntity<?> createTeams(@RequestBody TeamRequest request) {
        Team team = teamService.createTeam(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "team", team,
                "message", "Equipos creados exitosamente."
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeamById (@PathVariable UUID id) {
        teamService.deleteTeamById(id);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "message", "Equipo eliminado."
        ));
    }
}
