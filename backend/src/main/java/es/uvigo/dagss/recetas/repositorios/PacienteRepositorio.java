package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {
    @Query("select p from Paciente p where (:term is null or p.direccion.localidad LIKE lower(concat('%', :term, '%'))) AND (:term2 is null or p.nombre LIKE lower(concat('%', :term, '%')))")
    List<Paciente> findPacientesByNombreAndLocalidad(@Param("term") String term);
    Optional<Paciente> findPacienteByLogin(String login);
    Optional<Paciente> findPacienteByNumTarjetaSanitaria(String numTarjetaSanitaria);
//    @Query("select p from Paciente p where ((p.direccion.localidad) LIKE lower(concat('%', :term, '%')) OR (p.nombre) LIKE lower(concat('%', :term, '%')))")
//    List<Paciente> findPacientesByNombreOrLocalidad(@Param("term") String term);
}
