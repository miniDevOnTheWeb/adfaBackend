package com.adfa.adfa.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StadiumRequest {
	@NotBlank(message = "El nombre es obligatorio")
    private String name;
	@NotBlank(message = "La ubicacion es obligatoria")
    private String location;
}
