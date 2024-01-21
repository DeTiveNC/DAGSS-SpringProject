package es.uvigo.dagss.recetas.repositorios;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Repository
public interface CitaRepositorio extends JpaRepository<Cita, Long> {
    @Query("SELECT c FROM Cita c where (:term is null or c.medico.numeroColegiado like lower(concat('%',:term,'%'))) AND (:term2 is null or c.paciente.numTarjetaSanitaria like lower(concat('%',:term2,'%')))")
    List<Cita> findCitasByMedicoAndPaciente(@Param("term") String term, @Param("term2") String term2);
    @Query("select c from Cita c where c.paciente.numTarjetaSanitaria like lower(concat('%', :term, '%')) and c.estado = 'PLANIFICADA' ")
    List<Cita> findCitasByPacienteAndEstado(@Param("term") String term);
    @Query("SELECT c FROM Cita c WHERE c.medico = :medico AND c.fecha = :fecha")
    List<Cita> findAppointmentsByMedicoAndFecha(@Param("medico") Medico medico, @Param("fecha") Date fecha);
    @Query("SELECT c FROM Cita c WHERE c.fecha = :fecha")
    List<Cita> findAppointmentsByFecha( @Param("fecha") Date fecha);

    @Query("SELECT c FROM Cita c WHERE c.medico = :medico AND c.fecha = :fecha AND c.hora = :hora")
    Cita findAppointmentByMedicoAndFechaAndHora(@Param("medico") Medico medico, @Param("fecha") Date fecha, @Param("hora") Time hora);
    List<Cita> findCitasByMedico(Medico medico);
}
