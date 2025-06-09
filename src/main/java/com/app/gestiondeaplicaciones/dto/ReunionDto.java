package com.app.gestiondeaplicaciones.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReunionDto {
    private Integer id;
    private String titulo;
    private LocalDateTime fechaHora;
    private Boolean esPrivada;
    private String codigoAcceso;
    private Integer maxParticipantes;
    private Integer creadorId;
    private List<Integer> tematicas;
}