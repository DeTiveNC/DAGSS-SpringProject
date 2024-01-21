package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import java.util.*;


@Service
public class MedicoServiciosImpl implements MedicoServicios{
    private final int maxCajas=1;
    @Autowired
    private CitaRepositorio citaRepositorio;
    @Autowired
    private MedicoRepositorio medicoRepositorio;
    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    @Autowired
    private PrescripcionRepositorio prescripcionRepositorio;
    @Autowired
    private RecetaRepositorio recetaRepositorio;
    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;
    @Override
    public List<Cita> devolverCitasMedico(String login) {
        Optional<Medico> medicoBusq = medicoRepositorio.findMedicoByLogin(login);
        return medicoBusq.map(medico -> citaRepositorio.findCitasByMedico(medico)).orElse(null);
    }

    @Override
    public Cita ausenciaPaciente(String numTarjetaSanitaria, Cita cita) {
        List<Cita> pacienteOptional = citaRepositorio.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        TipoEstado estadoAusente = TipoEstado.AUSENTE;
        if (pacienteOptional.contains(cita)){
            cita.setEstado(estadoAusente);
            return citaRepositorio.save(cita);
        }
        return null;
    }

    @Override
    public Cita devolverCitaActual(String numColegiado, Date fechaCita,Time timeCita) {
        Optional<Medico> citaBusq = medicoRepositorio.findMedicoByNumeroColegiado(numColegiado);
        return citaBusq.map(
                medico -> citaRepositorio.findAppointmentByMedicoAndFechaAndHora(medico, fechaCita, timeCita))
                .orElse(null);
    }
    @Override
    public Cita anularCita(String numTarjetaSanitaria,Cita cita) {
        List<Cita> citaBusq = citaRepositorio.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        TipoEstado tipoEstadoAnulada = TipoEstado.ANULADA;
        if (citaBusq.contains(cita)){
            cita.setEstado(tipoEstadoAnulada);
            return citaRepositorio.save(cita);
        }
        return null;
    }

    @Override
    public Cita completarCita(String numTarjetaSanitaria, Cita cita) {
        List<Cita> citaList = citaRepositorio.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        TipoEstado estadoCompletada = TipoEstado.COMPLETADA;
        if (citaList.contains(cita)){
            cita.setEstado(estadoCompletada);
            return citaRepositorio.save(cita);
        }
        return null;
    }

    @Override
    public List<Medicamento> devolverMedicamentos(String valor1, String valor2, String valor3, String valor4) {
        return medicamentoRepositorio.findMedicamentosByNombreComercialAndFabricanteAndFamiliaAndPrincipioActivo(valor1,valor2,valor3,valor4);
    }

    @Override
    public Prescripcion crearPrescripcionMedico(Medicamento medicamento, String numColegiado, String numTarjetaSanitaria, Double dosis, String indicaciones, Date fechFinPres) {
        TipoEstado estadoActivo = TipoEstado.ACTIVO;
        Optional<Medico> medicoOptional = medicoRepositorio.findMedicoByNumeroColegiado(numColegiado);
        if(medicoOptional.isEmpty()){
            return null;
        }
        Optional<Paciente> pacienteOptional= pacienteRepositorio.findPacienteByNumTarjetaSanitaria(numTarjetaSanitaria);
        if(pacienteOptional.isEmpty()){
            return null;
        }
        Medico medico=medicoOptional.get();
        Paciente paciente= pacienteOptional.get();
        Prescripcion prescripcion = new Prescripcion(medicamento, medico, paciente, dosis, indicaciones, new Date(new java.util.Date().getTime()) , fechFinPres, estadoActivo);
        prescripcion = prescripcionRepositorio.save(prescripcion);
        generarRecetas(prescripcion, this.maxCajas);
        return prescripcion;
    }

    private void generarRecetas(Prescripcion prescripcion, int maxCajasPerRecipe){
        long intervalo= ChronoUnit.DAYS.between(prescripcion.getFechInPres().toLocalDate(),prescripcion.getFechFinPres().toLocalDate());
        Double totaldosis = prescripcion.getDosis()*(intervalo);
        int ncajas =   (int) (Math.ceil(totaldosis / (prescripcion.getMedicamento().getNumDosis()*maxCajasPerRecipe)));
        TipoEstado estadoPlanificada = TipoEstado.PLANIFICADA;
        for(long i=0;i<ncajas;i++){
            Date startDate=Date.valueOf(prescripcion.getFechInPres().toLocalDate().plusDays((i)*intervalo/ncajas));
            Date endDate=Date.valueOf(prescripcion.getFechInPres().toLocalDate().plusDays((i+1)*intervalo/ncajas));
            Receta receta= new Receta(prescripcion, i+1, startDate , endDate, maxCajasPerRecipe, estadoPlanificada, null);
            recetaRepositorio.save(receta);
        }
    }

    @Override
    public Medico viewMedico(Long id) {
        Optional<Medico> medicoView = medicoRepositorio.findById(id);
        return medicoView.orElse(null);
    }

    @Override
    public Medico editMedico(Long id, Medico editMedico) {
        Optional<Medico> medicoBusq = medicoRepositorio.findById(id);
        if (medicoBusq.isPresent() && medicoBusq.get().equals(editMedico)){
            return medicoRepositorio.save(editMedico);
        }
        return null;
    }
    @Override
    public List<Time> tiempoCitasOcupadas(String numColegiado, Date fecha) {
        Optional<Medico> medicoCita = medicoRepositorio.findMedicoByNumeroColegiado(numColegiado);
        if (medicoCita.isPresent() && fecha != null){
            List<Cita> citasActuales = citaRepositorio.findAppointmentsByMedicoAndFecha(medicoCita.get(), fecha);
            List<Time> horaDisponible = new ArrayList<>();
            for (Cita cita : citasActuales){
                horaDisponible.add(cita.getHora());
            }
            return horaDisponible;
        }
        return null;
    }
}
