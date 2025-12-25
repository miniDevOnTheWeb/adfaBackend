package com.adfa.adfa.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ScoreRequest {
    private UUID matchId;
    private Integer scoreLocal;
    private Integer scoreVisitor;
}
