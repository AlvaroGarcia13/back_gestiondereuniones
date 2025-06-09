package com.app.gestiondeaplicaciones.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParticipanteDto {
    private Integer usuarioId;
    private Integer reunionId;
    private LocalDateTime fechaUnion;
}