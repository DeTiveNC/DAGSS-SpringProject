package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepositorio extends JpaRepository<Receta, Receta> {
    @Query("SELECT r FROM Receta r WHERE r.estado = 'PLANIFICADA'")
    List<Receta> findRecetasByEstado();
}
