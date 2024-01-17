package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Medicamento implements Serializable {
    @Id
    private String nombreComercial;
    private String principioActivo;
    private String fabricante;
    private String familia;
    private Integer numDosis;
    private Boolean activo;

    public Medicamento() {
    }

    public Medicamento(String nombreComercial, String principioActivo, String fabricante, String familia, Integer numDosis, Boolean activo) {
        this.nombreComercial = nombreComercial;
        this.principioActivo = principioActivo;
        this.fabricante = fabricante;
        this.familia = familia;
        this.numDosis = numDosis;
        this.activo = activo;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getPrincipioActivo() {
        return principioActivo;
    }

    public void setPrincipioActivo(String principioActivo) {
        this.principioActivo = principioActivo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public Integer getNumDosis() {
        return numDosis;
    }

    public void setNumDosis(Integer numDosis) {
        this.numDosis = numDosis;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicamento that)) return false;
        return Objects.equals(getNombreComercial(), that.getNombreComercial());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombreComercial());
    }
}
