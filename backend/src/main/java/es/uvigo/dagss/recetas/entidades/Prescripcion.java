package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
public class Prescripcion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "medicamento_prescrito")
    private Medicamento medicamento;
    @OneToOne
    @JoinColumn(name = "medico_creador")
    private Medico medico;
    @OneToOne
    @JoinColumn(name = "paciente_prescrito")
    private Paciente paciente;
    private Double dosis;
    private String indicaciones;
    private Date fechInPres;
    private Date fechFinPres;
    @Enumerated(EnumType.STRING)
    @Column(name = "ESTADO", length = 20)
    protected TipoEstado estado;

    public Prescripcion() {
    }

    public Prescripcion(Long id, Medicamento medicamento, Medico medico, Paciente paciente, Double dosis, String indicaciones, Date fechInPres, Date fechFinPres, TipoEstado estado) {
        this.id = id;
        this.medicamento = medicamento;
        this.medico = medico;
        this.paciente = paciente;
        this.dosis = dosis;
        this.indicaciones = indicaciones;
        this.fechInPres = fechInPres;
        this.fechFinPres = fechFinPres;
        this.estado = estado;
    }

    public Prescripcion(TipoEstado estado){
        this();
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Double getDosis() {
        return dosis;
    }

    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }

    public String getIndicaciones() {
        return indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public Date getFechInPres() {
        return fechInPres;
    }

    public void setFechInPres(Date fechInPres) {
        this.fechInPres = fechInPres;
    }

    public Date getFechFinPres() {
        return fechFinPres;
    }

    public void setFechFinPres(Date fechFinPres) {
        this.fechFinPres = fechFinPres;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }
}
