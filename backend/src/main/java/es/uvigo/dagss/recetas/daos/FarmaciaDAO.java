package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmaciaDAO extends JpaRepository<Farmacia, Long> {
    @Query("Select f from Farmacia f where (:term is null or f.nombreEstablecimiento like lower(concat('%',:term,'%'))) AND (:term2 is null or f.direccion.localidad like lower(concat('%',:term2,'%')))")
    List<Farmacia> findFarmaciasByNombreEstablecimientoAndDireccionLocalidad(@Param("term") String term, @Param("term2") String term2);
}
