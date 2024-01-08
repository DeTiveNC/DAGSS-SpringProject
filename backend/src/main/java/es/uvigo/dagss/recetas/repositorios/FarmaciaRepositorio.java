package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FarmaciaRepositorio extends JpaRepository<Farmacia, Long> {
    @Query("Select f from Farmacia f where f.nombreEstablecimiento like lower(concat('%',:term,'%')) AND f.direccion.localidad like lower(concat('%',:term2,'%'))")
    List<Farmacia> findFarmaciasByNombreEstablecimientoAndDireccionLocalidad(@Param("term") String term, @Param("term2") String term2);
    Optional<Farmacia> findFarmaciaByLogin(String login);
}
