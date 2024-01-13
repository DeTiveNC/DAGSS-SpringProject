package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.repositorios.CitaRepositorio;
import es.uvigo.dagss.recetas.repositorios.PacienteRepositorio;
import es.uvigo.dagss.recetas.repositorios.PrescripcionRepositorio;
import es.uvigo.dagss.recetas.repositorios.RecetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Service
public class PacienteServiciosImpl implements PacienteServicios{
    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    @Autowired
    private CitaRepositorio citaRepositorio;
    @Autowired
    private RecetaRepositorio recetaRepositorio;
    @Autowired
    private PrescripcionRepositorio prescripcionRepositorio;
    @Override
    public List<Cita> devolverCitasPaciente(String login) {
        return citaRepositorio.findCitasByPacienteAndEstado(login);
    }

    @Override
    public Cita anularCitaPaciente(String login, Cita citaAnular) {
        List<Cita> citaOptional = citaRepositorio.findCitasByPacienteAndEstado(login);
        TipoEstado estadoAnular = TipoEstado.ANULADA;
        if (citaOptional.contains(citaAnular)){
            citaAnular.setEstado(estadoAnular);
            return citaRepositorio.save(citaAnular);
        }
        return null;
    }

    @Override
    public Cita crearCitaPaciente(String login, String numColegiado, Date fecha, Time hora) {
        return null;
    }

    @Override
    public List<Receta> obtenerRecetasPaciente(String login) {
        return recetaRepositorio.findRecetasByEstado(login);
    }

    @Override
    public List<Prescripcion> devolverPrescripcionPacientes(String numTarjetaSanitaria) {
        return prescripcionRepositorio.findPrescripcionsByPaciente(pacienteRepositorio.findPacienteByNumTarjetaSanitaria(numTarjetaSanitaria).get());
    }

    @Override
    public Paciente viewPaciente(String login) {
        return pacienteRepositorio.findPacienteByLogin(login).get();
    }

    @Override
    public Paciente editPaciente(Paciente paciente) {
        return pacienteRepositorio.save(paciente);
    }
}
