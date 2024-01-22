package es.uvigo.dagss.recetas.daos;

import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescripcionDAO extends JpaRepository<Prescripcion, Long> {
    List<Prescripcion> findPrescripcionsByPaciente(Paciente paciente);
}
