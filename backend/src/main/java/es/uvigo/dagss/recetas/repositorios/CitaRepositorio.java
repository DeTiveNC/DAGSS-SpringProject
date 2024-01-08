package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    @Query("SELECT c FROM Cita c where c.medico.nombre like lower(concat('%',:term,'%')) or c.paciente.numTarjetaSanitaria like lower(concat('%',:term2,'%'))")
    List<Cita> findCitasByMedicoOrPaciente(@Param("term") String term, @Param("term2") String term2);
    @Query("select c from Cita c where c.paciente.numTarjetaSanitaria like lower(concat('%', :term, '%')) and c.estado = 'PLANIFICADA' ")
    List<Cita> findCitasByPacienteAndEstado(@Param("term") String term);
    @Query("SELECT c FROM Cita c WHERE c.medico = :medico AND c.hora = :hora")
    List<Cita> findAppointmentsByMedicoAndFecha(@Param("medico") Medico medico, @Param("hora") Time hora);
}
