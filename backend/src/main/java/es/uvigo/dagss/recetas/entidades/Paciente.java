package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "PACIENTE")
public class Paciente extends Usuario {

	// Anadir atributos propios
    private String nombre;
    private String apellidos;
    private String dni;
    private String numTarjetaSanitaria;
    private String numSeguridadSocial;
    @Embedded
    private Direccion direccion;
    private String telefono;
    private String email;
    private Date fechaNacimiento;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centro_salud")
    private CentroDeSalud centroDeSalud;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_asig")
    private Medico medico;


    public Paciente() {
        super(TipoUsuario.PACIENTE);
    }

    public Paciente(String nombre, String apellidos, String dni, String numTarjetaSanitaria, String numSeguridadSocial, Direccion direccion, String telefono, String email, Date fechaNacimiento, CentroDeSalud centroDeSalud, Medico medico) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.numTarjetaSanitaria = numTarjetaSanitaria;
        this.numSeguridadSocial = numSeguridadSocial;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.centroDeSalud = centroDeSalud;
        this.medico = medico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumTarjetaSanitaria() {
        return numTarjetaSanitaria;
    }

    public void setNumTarjetaSanitaria(String numTarjetaSanitaria) {
        this.numTarjetaSanitaria = numTarjetaSanitaria;
    }

    public String getNumSeguridadSocial() {
        return numSeguridadSocial;
    }

    public void setNumSeguridadSocial(String numSeguridadSocial) {
        this.numSeguridadSocial = numSeguridadSocial;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public CentroDeSalud getCentroDeSalud() {
        return centroDeSalud;
    }

    public void setCentroDeSalud(CentroDeSalud centroDeSalud) {
        this.centroDeSalud = centroDeSalud;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
