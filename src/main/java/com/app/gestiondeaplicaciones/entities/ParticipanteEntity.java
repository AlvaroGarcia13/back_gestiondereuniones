package com.app.gestiondeaplicaciones.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "\"participante\"")
@Getter
@Setter
@NoArgsConstructor
public class ParticipanteEntity {

    @EmbeddedId
    private ParticipanteId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuarioId")
    @JoinColumn(name = "\"usuarioId\"")
    private UsuarioEntity usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("reunionId")
    @JoinColumn(name = "\"reunionId\"")
    private ReunionEntity reunion;

    @Column(name = "\"fechaUnion\"")
    private LocalDateTime fechaUnion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteEntity)) return false;
        ParticipanteEntity that = (ParticipanteEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}