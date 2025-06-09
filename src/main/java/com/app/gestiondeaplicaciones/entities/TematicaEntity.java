package com.app.gestiondeaplicaciones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "\"tematica\"")
@Getter
@Setter
@NoArgsConstructor
public class TematicaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"nombre\"", nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "tematicas", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ReunionEntity> reuniones = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TematicaEntity)) return false;
        TematicaEntity that = (TematicaEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}