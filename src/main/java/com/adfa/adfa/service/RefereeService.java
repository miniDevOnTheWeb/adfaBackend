package com.adfa.adfa.service;

import com.adfa.adfa.data.repository.RefereeRepository;
import com.adfa.adfa.model.dto.RefereeRequest;
import com.adfa.adfa.model.entity.Referee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RefereeService {
    private final RefereeRepository repository;

    public RefereeService(RefereeRepository repository) {
        this.repository = repository;
    }

    public Referee createReferee (RefereeRequest request) {
        Referee referee = new Referee();
        referee.setName(request.getName());
        referee.setAge(request.getAge());
        return repository.save(referee);
    }

    public List<Referee> getAllReferees() {
        return repository.findAll();
    }

    public void deleteAllReferees() {
        repository.deleteAll();
    }

    public void deleteRefereeById(UUID id) {
        repository.deleteById(id);
    }

    public Referee getRefereeById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Referee no encontrado"));
    }
}