package com.adfa.adfa.service;

import com.adfa.adfa.data.repository.MatchRepository;
import com.adfa.adfa.data.repository.StadiumRepository;
import com.adfa.adfa.data.repository.TeamRepository;
import com.adfa.adfa.model.dto.MatchRequest;
import com.adfa.adfa.model.dto.ScoreRequest;
import com.adfa.adfa.model.entity.Match;
import com.adfa.adfa.model.entity.Referee;
import com.adfa.adfa.model.entity.Stadium;
import com.adfa.adfa.model.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamService teamService;
    private final StadiumService stadiumService;
    private final RefereeService refereeService;

    public MatchService(MatchRepository matchRepository, TeamService teamService, StadiumService stadiumService, RefereeService refereeService) {
        this.matchRepository = matchRepository;
        this.teamService = teamService;
        this.stadiumService = stadiumService;
        this.refereeService = refereeService;
    }

    public Match createMatch (MatchRequest request) {
        Team local = teamService.getTeamById(request.getLocalId());
        Team visitor = teamService.getTeamById(request.getVisitorId());
        Stadium stadium = stadiumService.getStadiumById(request.getStadiumId());
        Referee referee = refereeService.getRefereeById(request.getRefereeId());

        Match match = new Match();
        match.setLocal(local);
        match.setVisitor(visitor);
        match.setStadium(stadium);
        match.setDate(request.getDate());
        match.setScoreLocal(request.getScoreLocal());
        match.setScoreVisitor(request.getScoreVisitor());
        match.setReferee(referee);
        match.setHour(request.getHour());

        return matchRepository.save(match);
    }

    public void deleteAllMatches() {
        matchRepository.deleteAll();
    }

    public void deleteMatchById(UUID id) {
        matchRepository.deleteById(id);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match setScore (ScoreRequest request) {
        Match match = matchRepository.findById(request.getMatchId()).orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        match.setScoreLocal(request.getScoreLocal());
        match.setScoreVisitor(request.getScoreVisitor());

        return matchRepository.save(match);
    }
}
