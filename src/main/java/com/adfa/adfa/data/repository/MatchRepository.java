package com.adfa.adfa.data.repository;

import com.adfa.adfa.enums.Category;
import com.adfa.adfa.model.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatchRepository extends JpaRepository<Match, UUID> {
    List<Match> findByCategory(Category category);

    @Query("select m from Match m where m.local.id = :teamId or m.visitor.id = :teamId")
    List<Match> findByTeamId(@Param("teamId") UUID teamId);
}
