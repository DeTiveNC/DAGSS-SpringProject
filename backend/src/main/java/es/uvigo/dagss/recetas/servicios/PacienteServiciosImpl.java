package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiciosImpl implements PacienteServicios{
    @Autowired
    private PacienteDAO pacienteDAO;
    @Autowired
    private CitaDAO citaDAO;
    @Autowired
    private RecetaDAO recetaDAO;
    @Autowired
    private PrescripcionDAO prescripcionDAO;
    @Autowired
    private MedicoDAO medicoDAO;
    @Autowired
    private MedicoServiciosImpl medicoServicios;
    @Override
    public List<Cita> devolverCitasPaciente(String numTarjetaSanitaria) {
        List<Cita> citas= citaDAO.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        for(Cita cambios:citas){
            cambios.setMedico(medicoDAO.findById(cambios.getMedico().getId()).get());
            cambios.setPaciente(pacienteDAO.findById(cambios.getPaciente().getId()).get());
        }
        return citas;
    }

    @Override
    public Cita anularCitaPaciente(String numTarjetaSanitaria, Cita citaAnular) {
        List<Cita> citaOptional = citaDAO.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        TipoEstado estadoAnular = TipoEstado.ANULADA;
        if (citaOptional.contains(citaAnular)){
            citaAnular.setEstado(estadoAnular);
            return citaDAO.save(citaAnular);
        }
        return null;
    }

    @Override
    public Cita crearCitaPaciente(Long id, Date fecha, Time horaCita) {
        Cita newCita = new Cita();
        TipoEstado citaEstadoCreacion = TipoEstado.PLANIFICADA;
        Float duracionCitaDefault = (float) 15;
        Optional<Paciente> pacienteCreadorCita = pacienteDAO.findById(id);
        if(!pacienteCreadorCita.isPresent()){
            return null;
        }
        Paciente paciente= pacienteCreadorCita.get();;
        Medico medico=paciente.getMedico();
        if (fecha != null && horaCita != null&&medico!=null){
            List<Time> tiempoOcupadoMedico = medicoServicios.tiempoCitasOcupadas(medico.getNumeroColegiado(), fecha);
            boolean contains = tiempoOcupadoMedico.contains(horaCita);
            if (!contains){
                newCita.setEstado(citaEstadoCreacion);
                newCita.setDuracion(duracionCitaDefault);
                newCita.setMedico(medico);
                newCita.setPaciente(paciente);
                newCita.setFecha(fecha);
                newCita.setHora(horaCita);
                return citaDAO.save(newCita);
            }
        }
        return null;
    }

    @Override
    public List<Receta> obtenerRecetasPaciente(String numTarjetaSanitaria) {
        return recetaDAO.findRecetasByEstado(numTarjetaSanitaria);
    }

    @Override
    public List<Prescripcion> devolverPrescripcionPacientes(String numTarjetaSanitaria) {
        Optional<Paciente> pacientePrescrip = pacienteDAO.findPacienteByNumTarjetaSanitaria(numTarjetaSanitaria);
        return pacientePrescrip.map(paciente -> prescripcionDAO.findPrescripcionsByPaciente(pacienteDAO.findPacienteByNumTarjetaSanitaria(numTarjetaSanitaria).get())).orElse(null);
    }

    @Override
    public Paciente viewPaciente(Long id) {
        Optional<Paciente> pacienteBusq = pacienteDAO.findById(id);
        return pacienteBusq.orElse(null);
    }

    @Override
    public Paciente editPaciente(Long id,Paciente editPaciente) {
        Optional<Paciente> pacientoBusq = pacienteDAO.findById(id);
        if (pacientoBusq.isPresent() && pacientoBusq.get().getId().equals(editPaciente.getId())){
            return pacienteDAO.save(editPaciente);
        }
        return null;
    }
     @Override
    public List<Time> tiempoCitasOcupadas(String numTarjetaSanitaria, Date fecha) {
        Optional<Paciente> paciente= pacienteDAO.findPacienteByNumTarjetaSanitaria(numTarjetaSanitaria);
        if(!paciente.isPresent()){
            return null;
        }
        Optional<Medico> medicoCita = medicoDAO.findMedicoByNumeroColegiado(paciente.get().getMedico().getNumeroColegiado());
        if (medicoCita.isPresent() && fecha != null){
            List<Cita> citasActuales = citaDAO.findAppointmentsByMedicoAndFecha(medicoCita.get(), fecha);
            List<Time> horaDisponible = new ArrayList<>();
            for (Cita cita : citasActuales){
                horaDisponible.add(cita.getHora());
            }
            return horaDisponible;
        }
        return null;
    }
}
