package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.repositorios.*;
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
    @Autowired
    private MedicoRepositorio medicoRepositorio;
    @Autowired
    private MedicoServiciosImpl medicoServicios;
    @Override
    public List<Cita> devolverCitasPaciente(String numTarjetaSanitaria) {
        List<Cita> citas= citaRepositorio.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        for(Cita cambios:citas){
            cambios.setMedico(medicoRepositorio.findById(cambios.getMedico().getId()).get());
            cambios.setPaciente(pacienteRepositorio.findById(cambios.getPaciente().getId()).get());
        }
        return citas;
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
    public Cita crearCitaPaciente(Long id, Date fecha, Time horaCita) {
        Cita newCita = new Cita();
        TipoEstado citaEstadoCreacion = TipoEstado.PLANIFICADA;
        Float duracionCitaDefault = (float) 15;
        Optional<Paciente> pacienteCreadorCita = pacienteRepositorio.findById(id);
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
                return citaRepositorio.save(newCita);
            }
        }
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
        if (pacientoBusq.isPresent() && pacientoBusq.get().getId().equals(editPaciente.getId())){
            return pacienteRepositorio.save(editPaciente);
        }
        return null;
    }
}
