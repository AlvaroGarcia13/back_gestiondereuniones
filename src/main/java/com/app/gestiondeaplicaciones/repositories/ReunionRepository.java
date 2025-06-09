package com.app.gestiondeaplicaciones.repositories;

import com.app.gestiondeaplicaciones.entities.ReunionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReunionRepository extends JpaRepository<ReunionEntity, Integer> {
}