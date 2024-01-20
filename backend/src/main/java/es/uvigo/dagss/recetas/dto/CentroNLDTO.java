package es.uvigo.dagss.recetas.dto;

public class CentroNLDTO {
    private String nombre;
    private String localidad;

    public CentroNLDTO() {
    }

    public CentroNLDTO(String nombre, String localidad) {
        this.nombre = nombre;
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
