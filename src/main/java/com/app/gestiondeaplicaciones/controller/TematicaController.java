package com.app.gestiondeaplicaciones.controller;

import com.app.gestiondeaplicaciones.dto.TematicaDto;
import com.app.gestiondeaplicaciones.entities.TematicaEntity;
import com.app.gestiondeaplicaciones.repositories.TematicaRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/tematicas")
public class TematicaController {
    private final TematicaRepository tematicaRepository;

    @GetMapping
    public List<TematicaDto> getAllTematicas() {
        List<TematicaEntity> entities = tematicaRepository.findAll();
        return entities.stream()
                .map(t -> {
                    TematicaDto dto = new TematicaDto();
                    dto.setId(t.getId());
                    dto.setNombre(t.getNombre());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}