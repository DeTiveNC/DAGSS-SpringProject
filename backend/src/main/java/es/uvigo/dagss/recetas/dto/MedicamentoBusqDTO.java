package es.uvigo.dagss.recetas.dto;

public class MedicamentoBusqDTO {
    private String nombreComercial;
    private String fabricante;
    private String familia;
    private String principioActivo;
    public MedicamentoBusqDTO() {
    }
    public MedicamentoBusqDTO(String nombreComercial, String fabricante, String familia, String principioActivo) {
        this.nombreComercial = nombreComercial;
        this.fabricante = fabricante;
        this.familia = familia;
        this.principioActivo = principioActivo;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
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

    public String getPrincipioActivo() {
        return principioActivo;
    }

    public void setPrincipioActivo(String principioActivo) {
        this.principioActivo = principioActivo;
    }
}
