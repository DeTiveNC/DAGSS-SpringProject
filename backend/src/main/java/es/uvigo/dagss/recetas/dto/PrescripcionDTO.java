package es.uvigo.dagss.recetas.dto;

import java.sql.Date;

public class PrescripcionDTO {
    private String numColegiado;
    private String numTarjetaSanitaria;
    private Double dosis;
    private String indicaciones;
    private Date fechFinPres;

    public PrescripcionDTO() {
    }

    public PrescripcionDTO(String numColegiado, Double dosis, String indicaciones, Date fechFinPres, String numTarjetaSanitaria) {
        this.numColegiado = numColegiado;
        this.dosis = dosis;
        this.indicaciones = indicaciones;
        this.fechFinPres = fechFinPres;
        this.numTarjetaSanitaria = numTarjetaSanitaria;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
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

    public Date getFechFinPres() {
        return fechFinPres;
    }

    public void setFechFinPres(Date fechFinPres) {
        this.fechFinPres = fechFinPres;
    }

    public String getNumTarjetaSanitaria() {
        return numTarjetaSanitaria;
    }

    public void setNumTarjetaSanitaria(String numTarjetaSanitaria) {
        this.numTarjetaSanitaria = numTarjetaSanitaria;
    }
}
