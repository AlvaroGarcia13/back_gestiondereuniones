package com.app.gestiondeaplicaciones.repositories;

import com.app.gestiondeaplicaciones.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    UsuarioEntity findByEmail(String email);
}