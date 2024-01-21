package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdministradorRepositorio extends JpaRepository<Administrador, Long> {
}
