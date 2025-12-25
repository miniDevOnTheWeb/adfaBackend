package com.adfa.adfa.data.repository;

import com.adfa.adfa.model.entity.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, UUID> {
}
