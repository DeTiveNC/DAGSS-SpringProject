package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.entidades.Receta;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface PacienteServicios {
    List<Cita> devolverCitasPaciente(String login);
    Cita anularCitaPaciente(String login,Cita citaAnular);
    Cita crearCitaPaciente(String login, String numColegiado, Date fecha, Time hora);
    List<Receta> obtenerRecetasPaciente(String login);
    List<Prescripcion> devolverPrescripcionPacientes(String numTarjetaSanitaria);
    Paciente viewPaciente(String login);
    Paciente editPaciente(Paciente paciente);
}
