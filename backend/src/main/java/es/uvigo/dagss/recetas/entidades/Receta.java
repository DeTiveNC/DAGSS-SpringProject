package es.uvigo.dagss.recetas.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity
@IdClass(RecetaId.class)
public class Receta implements Serializable {
    @Id
    @JoinColumn(name = "prescripcion_id")
    @ManyToOne
    private Prescripcion prescripcion;
    @Id
    @Column(name = "recetas_id")
    private Long id;
    @OrderBy
    private Date fechInVal;
    private Date fechFinVal;
    private Integer unid;
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 20)
    protected TipoEstado estado;
    @ManyToOne
    @JoinColumn(name = "farmacia_servida")
    private Farmacia farmacia;

    public Receta() {
    }

    public Receta(Prescripcion prescripcion, Long id, Date fechInVal, Date fechFinVal, Integer unid, TipoEstado estado, Farmacia farmacia) {
        this.prescripcion = prescripcion;
        this.id = id;
        this.fechInVal = fechInVal;
        this.fechFinVal = fechFinVal;
        this.unid = unid;
        this.estado = estado;
        this.farmacia = farmacia;
    }

    public Receta(TipoEstado estado){
        this();
        this.estado = estado;
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

    public Date getFechInVal() {
        return fechInVal;
    }

    public void setFechInVal(Date fechInVal) {
        this.fechInVal = fechInVal;
    }

    public Date getFechFinVal() {
        return fechFinVal;
    }

    public void setFechFinVal(Date fechFinVal) {
        this.fechFinVal = fechFinVal;
    }

    public Integer getUnid() {
        return unid;
    }

    public void setUnid(Integer unid) {
        this.unid = unid;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public Farmacia getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(Farmacia farmacia) {
        this.farmacia = farmacia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Receta receta)) return false;
        return Objects.equals(getPrescripcion().getId(), receta.getPrescripcion().getId()) &&
                Objects.equals(getId(), receta.getId()) &&
                Objects.equals(getFechInVal(), receta.getFechInVal()) &&
                Objects.equals(getFechFinVal(), receta.getFechFinVal()) &&
                Objects.equals(getUnid(), receta.getUnid()) &&
                getEstado() == receta.getEstado() &&
                Objects.equals(getFarmacia().getId(), receta.getFarmacia().getId());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getPrescripcion(), getId(), getFechInVal(), getFechFinVal(), getUnid(), getEstado(), getFarmacia());
    }
}
