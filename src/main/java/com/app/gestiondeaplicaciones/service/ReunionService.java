package com.app.gestiondeaplicaciones.service;

import com.app.gestiondeaplicaciones.dto.ReunionDto;
import com.app.gestiondeaplicaciones.dto.UsuarioDto;
import com.app.gestiondeaplicaciones.entities.ReunionEntity;
import com.app.gestiondeaplicaciones.entities.TematicaEntity;
import com.app.gestiondeaplicaciones.entities.UsuarioEntity;
import com.app.gestiondeaplicaciones.repositories.ReunionRepository;
import com.app.gestiondeaplicaciones.repositories.TematicaRepository;
import com.app.gestiondeaplicaciones.repositories.UsuarioRepository;
import com.app.gestiondeaplicaciones.entities.ParticipanteEntity;
import com.app.gestiondeaplicaciones.entities.ParticipanteId;
import com.app.gestiondeaplicaciones.repositories.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReunionService {
    @Autowired
    private ReunionRepository reunionRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TematicaRepository tematicaRepository;
    @Autowired
    private ParticipanteRepository participanteRepository;

    public ReunionEntity saveReunion(ReunionDto dto) {
        ReunionEntity reunion = new ReunionEntity();
        reunion.setTitulo(dto.getTitulo());
        reunion.setFechaHora(dto.getFechaHora());
        reunion.setEsPrivada(dto.getEsPrivada());
        reunion.setCodigoAcceso(dto.getCodigoAcceso());
        reunion.setMaxParticipantes(dto.getMaxParticipantes());

        UsuarioEntity creador = usuarioRepository.findById(dto.getCreadorId()).orElse(null);
        reunion.setCreador(creador);

        Set<TematicaEntity> tematicas = new HashSet<>();
        if (dto.getTematicas() != null) {
            for (Integer id : dto.getTematicas()) {
                tematicaRepository.findById(id).ifPresent(tematicas::add);
            }
        }
        reunion.setTematicas(tematicas);

        return reunionRepository.save(reunion);
    }

    public List<ReunionDto> getAllReunionesDto() {
        List<ReunionEntity> entities = reunionRepository.findAll();
        List<ReunionDto> dtos = new ArrayList<>();

        for (ReunionEntity entity : entities) {
            ReunionDto dto = new ReunionDto();
            dto.setId(entity.getId());
            dto.setTitulo(entity.getTitulo());
            dto.setFechaHora(entity.getFechaHora());
            dto.setEsPrivada(entity.getEsPrivada());
            dto.setCodigoAcceso(entity.getCodigoAcceso());
            dto.setMaxParticipantes(entity.getMaxParticipantes());
            dto.setCreadorId(entity.getCreador() != null ? entity.getCreador().getId() : null);
            // Convierte temáticas a lista de IDs
            List<Integer> tematicasIds = entity.getTematicas().stream()
                    .map(t -> t.getId())
                    .toList();
            dto.setTematicas(tematicasIds);
            dtos.add(dto);
        }
        return dtos;
    }

    public void unirUsuarioAReunion(Integer reunionId, Integer userId) {
        ReunionEntity reunion = reunionRepository.findById(reunionId)
                .orElseThrow(() -> new RuntimeException("Reunión no encontrada"));
        UsuarioEntity usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ParticipanteId participanteId = new ParticipanteId();
        participanteId.setReunionId(reunionId);
        participanteId.setUsuarioId(userId);

        // Evita duplicados
        if (participanteRepository.existsById(participanteId)) {
            return;
        }

        ParticipanteEntity participante = new ParticipanteEntity();
        participante.setId(participanteId);
        participante.setReunion(reunion);
        participante.setUsuario(usuario);
        participante.setFechaUnion(java.time.LocalDateTime.now());

        participanteRepository.save(participante);
    }

    public ReunionDto updateReunion(Integer id, ReunionDto dto) {
        ReunionEntity reunion = reunionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reunión no encontrada"));

        reunion.setTitulo(dto.getTitulo());
        reunion.setFechaHora(dto.getFechaHora());
        reunion.setEsPrivada(dto.getEsPrivada());
        reunion.setCodigoAcceso(dto.getCodigoAcceso());
        reunion.setMaxParticipantes(dto.getMaxParticipantes());

        if (dto.getCreadorId() != null) {
            UsuarioEntity creador = usuarioRepository.findById(dto.getCreadorId()).orElse(null);
            reunion.setCreador(creador);
        }

        if (dto.getTematicas() != null) {
            Set<TematicaEntity> tematicas = new HashSet<>();
            for (Integer tematicaId : dto.getTematicas()) {
                tematicaRepository.findById(tematicaId).ifPresent(tematicas::add);
            }
            reunion.setTematicas(tematicas);
        }

        ReunionEntity saved = reunionRepository.save(reunion);

        // Convertir a DTO para la respuesta
        ReunionDto updatedDto = new ReunionDto();
        updatedDto.setId(saved.getId());
        updatedDto.setTitulo(saved.getTitulo());
        updatedDto.setFechaHora(saved.getFechaHora());
        updatedDto.setEsPrivada(saved.getEsPrivada());
        updatedDto.setCodigoAcceso(saved.getCodigoAcceso());
        updatedDto.setMaxParticipantes(saved.getMaxParticipantes());
        updatedDto.setCreadorId(saved.getCreador() != null ? saved.getCreador().getId() : null);
        updatedDto.setTematicas(saved.getTematicas().stream().map(t -> t.getId()).toList());

        return updatedDto;
    }
    public void deleteReunion(Integer reunionId) {
        if (!reunionRepository.existsById(reunionId)) {
            throw new RuntimeException("Reunión no encontrada");
        }
        reunionRepository.deleteById(reunionId);
    }
    public List<ReunionDto> getReunionesByUsuario(Integer usuarioId) {
        List<ParticipanteEntity> participaciones = participanteRepository.findByUsuario_Id(usuarioId);
        List<ReunionDto> reuniones = new ArrayList<>();
        for (ParticipanteEntity p : participaciones) {
            ReunionEntity r = p.getReunion();
            ReunionDto dto = new ReunionDto();
            dto.setId(r.getId());
            dto.setTitulo(r.getTitulo());
            dto.setFechaHora(r.getFechaHora());
            dto.setEsPrivada(r.getEsPrivada());
            dto.setCodigoAcceso(r.getCodigoAcceso());
            dto.setMaxParticipantes(r.getMaxParticipantes());
            dto.setCreadorId(r.getCreador() != null ? r.getCreador().getId() : null);
            dto.setTematicas(r.getTematicas().stream().map(t -> t.getId()).toList());
            reuniones.add(dto);
        }
        return reuniones;
    }
    public void abandonarReunion(Integer reunionId, Integer usuarioId) {
        ParticipanteId participanteId = new ParticipanteId();
        participanteId.setReunionId(reunionId);
        participanteId.setUsuarioId(usuarioId);
        if (participanteRepository.existsById(participanteId)) {
            participanteRepository.deleteById(participanteId);
        }
    }
    public List<UsuarioDto> getParticipantesDeReunion(Integer reunionId) {
        List<UsuarioDto> participantes = new ArrayList<>();
        ReunionEntity reunion = reunionRepository.findById(reunionId)
                .orElseThrow(() -> new RuntimeException("Reunión no encontrada"));
        for (ParticipanteEntity p : reunion.getParticipaciones()) {
            UsuarioEntity usuario = p.getUsuario();
            UsuarioDto dto = new UsuarioDto();
            dto.setId(usuario.getId());
            dto.setName(usuario.getName());
            dto.setEmail(usuario.getEmail());
            participantes.add(dto);
        }
        return participantes;
    }
}