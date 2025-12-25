package com.adfa.adfa.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "referee")
@Table(name = "referees")
@NoArgsConstructor
@Getter
@Setter
public class Referee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;
}
