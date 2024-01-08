package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario {

    // Anadir atributos propios
	private String nombreEstablecimiento;
    private String nombreFarmaceutico;
    private String apellidosFarmaceutico;
    private String dni;
    private String numColegiado;
    @Embedded
    private Direccion direccion;
    private String telefono;
    private String email;
    public Farmacia() {
        super(TipoUsuario.FARMACIA);
    }
    public Farmacia(String nombreEstablecimiento, String nombreFarmaceutico, String apellidosFarmaceutico, String dni, String numColegiado, Direccion direccion, String telefono, String email) {
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.nombreFarmaceutico = nombreFarmaceutico;
        this.apellidosFarmaceutico = apellidosFarmaceutico;
        this.dni = dni;
        this.numColegiado = numColegiado;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public String getNombreFarmaceutico() {
        return nombreFarmaceutico;
    }

    public void setNombreFarmaceutico(String nombreFarmaceutico) {
        this.nombreFarmaceutico = nombreFarmaceutico;
    }

    public String getApellidosFarmaceutico() {
        return apellidosFarmaceutico;
    }

    public void setApellidosFarmaceutico(String apellidosFarmaceutico) {
        this.apellidosFarmaceutico = apellidosFarmaceutico;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
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
}
