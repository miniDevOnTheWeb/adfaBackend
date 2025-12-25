package com.adfa.adfa.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
public class MatchRequest {
    private UUID localId;
    private UUID visitorId;
    private UUID stadiumId;
    private LocalDate date;
    private Integer scoreLocal;
    private Integer scoreVisitor;
    private UUID refereeId;
    private LocalTime hour;
}
