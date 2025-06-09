package com.app.gestiondeaplicaciones.repositories;

import com.app.gestiondeaplicaciones.entities.ParticipanteEntity;
import com.app.gestiondeaplicaciones.entities.ParticipanteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<ParticipanteEntity, ParticipanteId> {
    List<ParticipanteEntity> findByUsuario_Id(Integer usuarioId);
}