package com.adfa.adfa.model.dto;

import com.adfa.adfa.enums.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TeamRequest {
    private String name;
    private String city;
    private Category category;
    private UUID stadiumId;
}
