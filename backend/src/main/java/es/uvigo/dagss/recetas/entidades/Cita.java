package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Cita implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id")
    private Medico medico;
    private Date fecha;
    @OrderBy
    private Time hora;
    private Float duracion;
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 20)
    protected TipoEstado estado;

    public Cita() {
    }

    public Cita( Paciente paciente, Medico medico,Date fecha, Time hora, Float duracion, TipoEstado estado) {
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.duracion = duracion;
        this.estado = estado;
    }

    public Cita(TipoEstado estado) {
        this();
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Float getDuracion() {
        return duracion;
    }

    public void setDuracion(Float duracion) {
        this.duracion = duracion;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cita cita)) return false;
        return Objects.equals(getId(), cita.getId()) && Objects.equals(getPaciente(), cita.getPaciente()) && Objects.equals(getMedico(), cita.getMedico());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPaciente(), getMedico());
    }
}
