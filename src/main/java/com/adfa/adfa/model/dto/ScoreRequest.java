package com.adfa.adfa.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ScoreRequest {
	@NotNull(message = "El partido a modificar es obligatorio")
    private UUID matchId;
	@NotNull(message = "El marcador del local es obligatorio")
    private Integer scoreLocal;
	@NotNull(message = "El marcador del visitante es obligatorio")
    private Integer scoreVisitor;
}
