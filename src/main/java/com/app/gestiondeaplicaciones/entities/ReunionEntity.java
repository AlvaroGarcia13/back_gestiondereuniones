package com.app.gestiondeaplicaciones.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "\"reunion\"")
@Getter
@Setter
@NoArgsConstructor
public class ReunionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"titulo\"")
    private String titulo;

    @Column(name = "\"fechaHora\"")
    private LocalDateTime fechaHora;

    @Column(name = "\"esPrivada\"")
    private Boolean esPrivada;

    @Column(name = "\"codigoAcceso\"")
    private String codigoAcceso;

    @Column(name = "\"maxParticipantes\"")
    private Integer maxParticipantes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"creadorId\"")
    private UsuarioEntity creador;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "\"reunionTematica\"",
            joinColumns = @JoinColumn(name = "\"reunionId\""),
            inverseJoinColumns = @JoinColumn(name = "\"tematicaId\"")
    )
    @JsonIgnore
    private Set<TematicaEntity> tematicas = new HashSet<>();

    @OneToMany(mappedBy = "reunion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ParticipanteEntity> participaciones = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReunionEntity)) return false;
        ReunionEntity that = (ReunionEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}