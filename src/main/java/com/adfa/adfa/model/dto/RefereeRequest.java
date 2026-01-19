package com.adfa.adfa.model.dto;

import jakarta.validation.constraints.Min;
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
    @Min(message = "La edad minima es 18 anos", value = 18)
    private Integer age;
    @NotBlank(message = "El numero de telefono del arbitro es obligatorio")
    private String phoneNumber;
}
