package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CentroDeSaludDAO extends JpaRepository<CentroDeSalud, String> {
    @Query("SELECT c FROM CentroDeSalud c WHERE (:term is null or LOWER(c.nombre) LIKE LOWER(CONCAT('%', :term, '%'))) AND (:term2 is null or LOWER(c.direccion.localidad) LIKE LOWER(CONCAT('%', :term2, '%')))")
    List<CentroDeSalud> findCentroDeSaludsByNombreAndDireccion(@Param("term") String term, @Param("term2") String term2);
    Optional<CentroDeSalud> findCentroDeSaludByNombre(String nombre);
    @Query("SELECT c FROM CentroDeSalud c WHERE (:term is null or LOWER(c.nombre) LIKE LOWER(CONCAT('%', :term, '%'))) AND (:term2 is null or LOWER(c.direccion.provincia) LIKE LOWER(CONCAT('%', :term2, '%')))")
    List<CentroDeSalud> findCentroDeSaludsByNombreAndDireccionProvincia(@Param("term") String term, @Param("term2") String term2);

}
