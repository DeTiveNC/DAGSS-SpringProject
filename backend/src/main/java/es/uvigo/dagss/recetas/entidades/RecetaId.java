package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.util.Objects;

public class RecetaId implements Serializable {
    private Prescripcion prescripcion;
    private Long id;

    public RecetaId(Prescripcion prescripcion, Long id) {
        this.prescripcion = prescripcion;
        this.id = id;
    }

    public RecetaId() {
    }

    public Prescripcion getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(Prescripcion prescripcion) {
        this.prescripcion = prescripcion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecetaId recetaId = (RecetaId) o;
        return Objects.equals(prescripcion, recetaId.prescripcion) &&
                Objects.equals(id, recetaId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prescripcion, id);
    }
}

