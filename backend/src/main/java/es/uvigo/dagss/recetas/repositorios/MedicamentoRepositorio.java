package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepositorio extends JpaRepository<Medicamento, String> {
    @Query("SELECT med FROM Medicamento med WHERE med.nombreComercial like lower(concat('%', :term,'%')) or med.principioActivo like lower(concat('%', :term2 ,'%')) or med.fabricante like lower(concat('%', :term3,'%')) or med.familia like lower(concat('%', :term4 ,'%'))")
    List<Medicamento> findMedicamentosByNombreComercialOrFabricanteOrFamiliaOrPrincipioActivo(@Param("term") String term,@Param("term2") String term2,@Param("term3") String term3,@Param("term4") String term4);
}
