package es.uvigo.dagss.recetas.entidades;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "ADMINISTRADOR")
public class Administrador extends Usuario {

    // Anadir atributos propios
    @TableGenerator(name = "ADMINISTRADOR_GEN", table = "ADMINISTRADOR_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ADMINISTRADOR_GEN")
    @Id
    private Long id;
    private String nombre;
    private String email;

    public Administrador() {
        super(TipoUsuario.ADMINISTRADOR); 
    }

    public Administrador(String nombre, String email) {
        super(TipoUsuario.ADMINISTRADOR);
        this.nombre = nombre;
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
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
