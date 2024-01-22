package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdministradorDAO extends JpaRepository<Administrador, Long> {
}
