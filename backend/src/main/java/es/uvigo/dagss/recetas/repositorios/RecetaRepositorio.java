package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecetaRepositorio extends JpaRepository<Receta, Receta> {
    @Query("SELECT r FROM Receta r WHERE (:term is null or r.estado = 'PLANIFICADA' AND r.prescripcion.paciente.numTarjetaSanitaria = :term)")
    List<Receta> findRecetasByEstado(@Param("term") String term);
    @Query("select r from Receta r where (:term = r.id and :term2 = r.prescripcion.id and r.prescripcion.paciente.numTarjetaSanitaria = :term3)")
    Optional<Receta> findRecetaByID(@Param("term") Long term, @Param("term2") Long term2, @Param("term3") String numTarjetaSanitaria );

}
