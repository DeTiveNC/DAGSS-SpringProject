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
import java.util.Optional;

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
    public List<Cita> devolverCitasPaciente(String numTarjetaSanitaria) {
        return citaRepositorio.findCitasByPacienteAndEstado(numTarjetaSanitaria);
    }

    @Override
    public Cita anularCitaPaciente(String numTarjetaSanitaria, Cita citaAnular) {
        List<Cita> citaOptional = citaRepositorio.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        TipoEstado estadoAnular = TipoEstado.ANULADA;
        if (citaOptional.contains(citaAnular)){
            citaAnular.setEstado(estadoAnular);
            return citaRepositorio.save(citaAnular);
        }
        return null;
    }

    @Override
    public Cita crearCitaPaciente(Long id, String numColegiado, Date fecha, Time hora) {
        return null;
    }

    @Override
    public List<Receta> obtenerRecetasPaciente(String numTarjetaSanitaria) {
        return recetaRepositorio.findRecetasByEstado(numTarjetaSanitaria);
    }

    @Override
    public List<Prescripcion> devolverPrescripcionPacientes(String numTarjetaSanitaria) {
        Optional<Paciente> pacientePrescrip = pacienteRepositorio.findPacienteByNumTarjetaSanitaria(numTarjetaSanitaria);
        return pacientePrescrip.map(paciente -> prescripcionRepositorio.findPrescripcionsByPaciente(pacienteRepositorio.findPacienteByNumTarjetaSanitaria(numTarjetaSanitaria).get())).orElse(null);
    }

    @Override
    public Paciente viewPaciente(Long id) {
        Optional<Paciente> pacienteBusq = pacienteRepositorio.findById(id);
        return pacienteBusq.orElse(null);
    }

    @Override
    public Paciente editPaciente(Long id,Paciente editPaciente) {
        Optional<Paciente> pacientoBusq = pacienteRepositorio.findById(id);
        if (pacientoBusq.isPresent() && pacientoBusq.get().equals(editPaciente)){
            pacienteRepositorio.save(editPaciente);
        }
        return null;
    }
}
