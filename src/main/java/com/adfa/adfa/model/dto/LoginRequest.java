package com.adfa.adfa.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {
	@NotBlank(message = "El nombre de usuario es obligatorio.")
    private String username;

	@NotBlank(message = "La contrasenia es obligatoria.")
    private String password;
}
