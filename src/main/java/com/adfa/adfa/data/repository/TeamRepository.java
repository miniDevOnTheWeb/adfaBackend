package com.adfa.adfa.data.repository;

import com.adfa.adfa.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
}
