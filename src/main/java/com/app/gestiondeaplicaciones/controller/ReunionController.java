package com.app.gestiondeaplicaciones.controller;

import com.app.gestiondeaplicaciones.dto.ReunionDto;
import com.app.gestiondeaplicaciones.entities.ReunionEntity;
import com.app.gestiondeaplicaciones.service.ReunionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("reuniones")
public class ReunionController {
    private final ReunionService reunionService;

    @PostMapping("/create")
    public ResponseEntity<ReunionEntity> createReunion(@RequestBody ReunionDto reunionDto) {
        ReunionEntity created = reunionService.saveReunion(reunionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    @GetMapping("/all")
    public List<ReunionDto> getAllReuniones() {
        return reunionService.getAllReunionesDto();
    }
    @PostMapping("/{reunionId}/unir/{userId}")
    public ResponseEntity<Void> unirUsuarioAReunion(@PathVariable Integer reunionId, @PathVariable Integer userId) {
        reunionService.unirUsuarioAReunion(reunionId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReunionDto> actualizarReunion(@PathVariable Integer id, @RequestBody ReunionDto reunionDto) {
        ReunionDto updated = reunionService.updateReunion(id, reunionDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{reunionId}/delete")
    public ResponseEntity<Void> borrarReunion(@PathVariable Integer reunionId) {
        reunionService.deleteReunion(reunionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/participante/usuario/{usuarioId}/reuniones")
    public List<ReunionDto> getReunionesByUsuario(@PathVariable Integer usuarioId) {
        return reunionService.getReunionesByUsuario(usuarioId);
    }

    @PostMapping("/{reunionId}/abandonar/{usuarioId}")
    public ResponseEntity<Void> abandonarReunion(@PathVariable Integer reunionId, @PathVariable Integer usuarioId) {
        reunionService.abandonarReunion(reunionId, usuarioId);
        return ResponseEntity.noContent().build();
    }


}