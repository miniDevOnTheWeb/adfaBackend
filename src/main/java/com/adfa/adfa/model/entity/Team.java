package com.adfa.adfa.model.entity;

import com.adfa.adfa.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "team")
@Table(name = "teams")
@NoArgsConstructor
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

	@Column(name = "points")
	private Integer points;

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;
}
