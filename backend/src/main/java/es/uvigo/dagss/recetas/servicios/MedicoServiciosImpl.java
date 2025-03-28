package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.daos.*;
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
    private CitaDAO citaDAO;
    @Autowired
    private MedicoDAO medicoDAO;
    @Autowired
    private PacienteDAO pacienteDAO;
    @Autowired
    private PrescripcionDAO prescripcionDAO;
    @Autowired
    private RecetaDAO recetaDAO;
    @Autowired
    private MedicamentoDAO medicamentoDAO;
    @Override
    public List<Cita> devolverCitasMedico(String login) {
        Optional<Medico> medicoBusq = medicoDAO.findMedicoByLogin(login);
        return medicoBusq.map(medico -> citaDAO.findCitasByMedico(medico)).orElse(null);
    }

    @Override
    public Cita ausenciaPaciente(String numTarjetaSanitaria, Cita cita) {
        List<Cita> pacienteOptional = citaDAO.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        TipoEstado estadoAusente = TipoEstado.AUSENTE;
        if (pacienteOptional.contains(cita)){
            cita.setEstado(estadoAusente);
            return citaDAO.save(cita);
        }
        return null;
    }

    @Override
    public Cita devolverCitaActual(String numColegiado, Date fechaCita,Time timeCita) {
        Optional<Medico> citaBusq = medicoDAO.findMedicoByNumeroColegiado(numColegiado);
        return citaBusq.map(
                medico -> citaDAO.findAppointmentByMedicoAndFechaAndHora(medico, fechaCita, timeCita))
                .orElse(null);
    }
    @Override
    public Cita anularCita(String numTarjetaSanitaria,Cita cita) {
        List<Cita> citaBusq = citaDAO.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        TipoEstado tipoEstadoAnulada = TipoEstado.ANULADA;
        if (citaBusq.contains(cita)){
            cita.setEstado(tipoEstadoAnulada);
            return citaDAO.save(cita);
        }
        return null;
    }

    @Override
    public Cita completarCita(String numTarjetaSanitaria, Cita cita) {
        List<Cita> citaList = citaDAO.findCitasByPacienteAndEstado(numTarjetaSanitaria);
        TipoEstado estadoCompletada = TipoEstado.COMPLETADA;
        System.out.println(citaList);
        System.out.println(citaList.contains(cita));
        if (citaList.contains(cita)){
            cita.setEstado(estadoCompletada);
            return citaDAO.save(cita);
        }
        return null;
    }

    @Override
    public List<Medicamento> devolverMedicamentos(String valor1, String valor2, String valor3, String valor4) {
        return medicamentoDAO.findMedicamentosByNombreComercialAndFabricanteAndFamiliaAndPrincipioActivo(valor1,valor2,valor3,valor4);
    }

    @Override
    public Prescripcion crearPrescripcionMedico(Medicamento medicamento, String numColegiado, String numTarjetaSanitaria, Double dosis, String indicaciones, Date fechFinPres) {
        TipoEstado estadoActivo = TipoEstado.ACTIVO;
        Optional<Medico> medicoOptional = medicoDAO.findMedicoByNumeroColegiado(numColegiado);
        if(medicoOptional.isEmpty()){
            return null;
        }
        Optional<Paciente> pacienteOptional= pacienteDAO.findPacienteByNumTarjetaSanitaria(numTarjetaSanitaria);
        if(pacienteOptional.isEmpty()){
            return null;
        }
        Medico medico=medicoOptional.get();
        Paciente paciente= pacienteOptional.get();
        Prescripcion prescripcion = new Prescripcion(medicamento, medico, paciente, dosis, indicaciones, new Date(new java.util.Date().getTime()) , fechFinPres, estadoActivo);
        prescripcion = prescripcionDAO.save(prescripcion);
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
            recetaDAO.save(receta);
        }
    }

    @Override
    public Medico viewMedico(Long id) {
        Optional<Medico> medicoView = medicoDAO.findById(id);
        return medicoView.orElse(null);
    }

    @Override
    public Medico editMedico(Long id, Medico editMedico) {
        Optional<Medico> medicoBusq = medicoDAO.findById(id);
        if (medicoBusq.isPresent() && medicoBusq.get().getId().equals(editMedico.getId())){
            return medicoDAO.save(editMedico);
        }
        return null;
    }
    @Override
    public List<Time> tiempoCitasOcupadas(String numColegiado, Date fecha) {
        Optional<Medico> medicoCita = medicoDAO.findMedicoByNumeroColegiado(numColegiado);
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
