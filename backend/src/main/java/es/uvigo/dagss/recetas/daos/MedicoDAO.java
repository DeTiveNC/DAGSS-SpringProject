package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoDAO extends JpaRepository<Medico, Long> {
    @Query("select m from Medico m where (:term is null or ((m.centroDeSalud.direccion.localidad) LIKE lower(concat('%', :term, '%'))) and (:term2 is null or (m.nombre) LIKE lower(concat('%', :term2, '%'))))")
    List<Medico> findMedicosByNombreAndCentroDeSaludDireccionLocalidad(@Param("term") String term, @Param("term2") String term2);
    Optional<Medico> findMedicoByLogin(String login);
    Optional<Medico> findMedicoByNumeroColegiado(String numColegiado);
    @Query("select m from Medico m where (:term is null or ((m.centroDeSalud.direccion.provincia) LIKE lower(concat('%', :term, '%'))) and (:term2 is null or (m.nombre) LIKE lower(concat('%', :term2, '%'))))")
    List<Medico> findMedicosByNombreAndCentroDeSaludDireccionProvincia(@Param("term") String term, @Param("term2") String term2);
}
