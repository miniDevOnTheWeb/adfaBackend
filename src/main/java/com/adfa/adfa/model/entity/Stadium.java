package com.adfa.adfa.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "stadium")
@Table(name = "stadiums")
@NoArgsConstructor
@Getter
@Setter
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;
}
