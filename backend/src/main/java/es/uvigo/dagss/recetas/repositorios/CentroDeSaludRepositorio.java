package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentroDeSaludRepositorio extends JpaRepository<CentroDeSalud, String> {
    @Query("SELECT c FROM CentroDeSalud c WHERE LOWER(c.nombre) LIKE LOWER(CONCAT('%', :term, '%')) OR LOWER(c.direccion.localidad) LIKE LOWER(CONCAT('%', :term2, '%'))")
    List<CentroDeSalud> findCentroDeSaludsByNombreOrDireccion(@Param("term") String term, @Param("term2") String term2);
}
