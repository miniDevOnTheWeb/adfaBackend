package com.adfa.adfa.service;

import com.adfa.adfa.data.repository.StadiumRepository;
import com.adfa.adfa.data.repository.TeamRepository;
import com.adfa.adfa.model.dto.TeamRequest;
import com.adfa.adfa.model.entity.Stadium;
import com.adfa.adfa.model.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final StadiumRepository stadiumRepository;

    public TeamService(TeamRepository teamRepository, StadiumRepository stadiumRepository) {
        this.teamRepository = teamRepository;
        this.stadiumRepository = stadiumRepository;
    }

    public Team createTeam (TeamRequest request) {
        Stadium stadium = stadiumRepository.findById(request.getStadiumId())
                .orElseThrow(() -> new RuntimeException("Estadio no encontrado"));
        Team team = new Team();
        team.setName(request.getName());
        team.setCity(request.getCity());
        team.setCategory(request.getCategory());
        team.setStadium(stadium);
        return teamRepository.save(team);
    }

    public void deleteAllTeams() {
        teamRepository.deleteAll();
    }

    public void deleteTeamById(UUID id) {
        teamRepository.deleteById(id);
    }

    public Team getTeamById(UUID id) {
        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
}
