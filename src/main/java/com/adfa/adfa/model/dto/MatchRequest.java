package com.adfa.adfa.model.dto;

import com.adfa.adfa.enums.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
public class MatchRequest {
	@NotNull(message = "El id del equipo local es obligatorio")
    private UUID localId;
	@NotNull(message = "El id del equipo visitante es obligatorio")
    private UUID visitorId;
	@NotNull(message = "El estadio es obligatorio")
    private UUID stadiumId;
	@NotNull(message = "La fecha es obligatoria")
    private LocalDate date;
    private Integer scoreLocal;
    private Integer scoreVisitor;
	@NotNull(message = "El arbitro es obligatorio")
    private UUID refereeId;
	@NotNull(message = "La hora del partido es obligatorio")
    private LocalTime hour;
    @NotNull(message = "La jornada es obligatoria")
    private Integer matchDay;
    private Category category;
}
