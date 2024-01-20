package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface PacienteServicios {
    List<Cita> devolverCitasPaciente(String numTarjetaSanitaria);
    Cita anularCitaPaciente(String numTarjetaSanitaria,Cita citaAnular);
    Cita crearCitaPaciente(Long id, Date fecha, Time hora);
    List<Receta> obtenerRecetasPaciente(String numTarjetaSanitaria);
    List<Prescripcion> devolverPrescripcionPacientes(String numTarjetaSanitaria);
    Paciente viewPaciente(Long id);
    Paciente editPaciente(Long id,Paciente paciente);
}
