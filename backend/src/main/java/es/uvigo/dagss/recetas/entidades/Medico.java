package es.uvigo.dagss.recetas.entidades;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario {

    // Anadir atributos propios
    private String nombre;
    private String apellidos;
    private String dni;
    private String numeroColegiado;
    private String telefono;
    @OneToMany(mappedBy = "medico", fetch = FetchType.EAGER)
    private List<Cita> citas;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "centro_medico")
    private CentroDeSalud centroDeSalud;

    public Medico() {
        super(TipoUsuario.MEDICO);
        this.citas = new ArrayList<>();
    }
    public Medico(String nombre, String apellidos, String dni, String numeroColegiado, String telefono, List<Cita> citas, CentroDeSalud centroDeSalud) {
        super(TipoUsuario.MEDICO);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.numeroColegiado = numeroColegiado;
        this.telefono = telefono;
        this.citas = citas;
        this.centroDeSalud = centroDeSalud;
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

    public String getNumeroColegiado() {
        return numeroColegiado;
    }

    public void setNumeroColegiado(String numeroColegiado) {
        this.numeroColegiado = numeroColegiado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public CentroDeSalud getCentroDeSalud() {
        return centroDeSalud;
    }

    public void setCentroDeSalud(CentroDeSalud centroDeSalud) {
        this.centroDeSalud = centroDeSalud;
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

}
