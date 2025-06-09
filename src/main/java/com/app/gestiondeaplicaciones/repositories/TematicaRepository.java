package com.app.gestiondeaplicaciones.repositories;

import com.app.gestiondeaplicaciones.entities.TematicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TematicaRepository extends JpaRepository<TematicaEntity, Integer> {
}