package com.adfa.adfa.model.entity;

import com.adfa.adfa.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "matches")
@NoArgsConstructor
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Team local;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = false)
    private Team visitor;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "score_local")
    private Integer scoreLocal;

    @Column(name = "score_visitor")
    private Integer scoreVisitor;

    @ManyToOne
    @JoinColumn(name = "referee_id", nullable = false)
    private Referee referee;

    @Column(name = "hour")
    private LocalTime hour;

    @Column(name = "match_day")
    private Integer matchDay;

    @Column(name = "category")
    private Category category;
}
