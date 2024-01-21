package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue(value = "FARMACIA")
public class Farmacia extends Usuario {

    // Anadir atributos propios
    @TableGenerator(name = "FARMACIA_GEN", table = "FARMACIA_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "FARMACIA_GEN")
    @Id
    private Long id;
	private String nombreEstablecimiento;
    private String nombreFarmaceutico;
    private String apellidosFarmaceutico;
    private String dni;
    @Column(unique = true)
    private String numColegiado;
    @Embedded
    private Direccion direccion;
    private String telefono;
    private String email;
    public Farmacia() {
        super(TipoUsuario.FARMACIA);
    }
    public Farmacia(String nombreEstablecimiento, String nombreFarmaceutico, String apellidosFarmaceutico, String dni, String numColegiado, Direccion direccion, String telefono, String email) {
        super(TipoUsuario.FARMACIA);
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.nombreFarmaceutico = nombreFarmaceutico;
        this.apellidosFarmaceutico = apellidosFarmaceutico;
        this.dni = dni;
        this.numColegiado = numColegiado;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
    }
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Farmacia farmacia)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getId(), farmacia.getId()) && Objects.equals(getNombreEstablecimiento(), farmacia.getNombreEstablecimiento()) && Objects.equals(getNombreFarmaceutico(), farmacia.getNombreFarmaceutico()) && Objects.equals(getApellidosFarmaceutico(), farmacia.getApellidosFarmaceutico()) && Objects.equals(getDni(), farmacia.getDni()) && Objects.equals(getNumColegiado(), farmacia.getNumColegiado()) && Objects.equals(getDireccion(), farmacia.getDireccion()) && Objects.equals(getTelefono(), farmacia.getTelefono()) && Objects.equals(getEmail(), farmacia.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getNombreEstablecimiento(), getNombreFarmaceutico(), getApellidosFarmaceutico(), getDni(), getNumColegiado(), getDireccion(), getTelefono(), getEmail());
    }
}
