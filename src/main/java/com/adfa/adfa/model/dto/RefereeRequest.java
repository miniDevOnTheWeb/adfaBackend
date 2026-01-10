package com.adfa.adfa.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RefereeRequest {
	@NotBlank(message = "El nombre es obligatorio")
    private String name;
	@NotNull(message = "La edad del arbitro es obligatoria")
    private Integer age;
}
