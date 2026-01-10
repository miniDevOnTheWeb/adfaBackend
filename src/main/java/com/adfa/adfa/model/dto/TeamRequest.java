package com.adfa.adfa.model.dto;

import com.adfa.adfa.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TeamRequest {
	@NotBlank(message = "El nombre es obligatorio")
    private String name;
	@NotBlank(message = "La ciudad es obligatoria")
    private String city;
	@NotNull(message = "La categoria no es valida")
    private Category category;
	@NotNull(message = "El estadio es obligatorio")
    private UUID stadiumId;
}
