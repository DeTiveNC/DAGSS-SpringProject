package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class CentroDeSalud implements Serializable {
    @Id
    private String nombre;
    @Embedded
    private Direccion direccion;
    private String telefono;
    private String email;
    private Boolean activo;

    public CentroDeSalud() {
    }

    public CentroDeSalud(String nombre, Direccion direccion, String telefono, String email, Boolean activo) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
