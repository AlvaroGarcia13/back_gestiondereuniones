package com.app.gestiondeaplicaciones.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ParticipanteId implements Serializable {
    private Integer reunionId;
    private Integer usuarioId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParticipanteId)) return false;
        ParticipanteId that = (ParticipanteId) o;
        return Objects.equals(reunionId, that.reunionId) &&
                Objects.equals(usuarioId, that.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reunionId, usuarioId);
    }
}