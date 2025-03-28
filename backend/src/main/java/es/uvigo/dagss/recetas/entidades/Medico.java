package es.uvigo.dagss.recetas.entidades;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "MEDICO")
public class Medico extends Usuario {

    // Anadir atributos propios
    @TableGenerator(name = "MEDICO_GEN", table = "MEDICO_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEDICO_GEN")
    @Id
    private Long id;
    private String nombre;
    private String apellidos;
    private String dni;
    @Column(unique = true)
    private String numeroColegiado;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "centro_medico")
    private CentroDeSalud centroDeSalud;

    public Medico() {
        super(TipoUsuario.MEDICO);
    }
    public Medico(String nombre, String apellidos, String dni, String numeroColegiado, String telefono, CentroDeSalud centroDeSalud) {
        super(TipoUsuario.MEDICO);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.numeroColegiado = numeroColegiado;
        this.telefono = telefono;
        this.centroDeSalud = centroDeSalud;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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
}
