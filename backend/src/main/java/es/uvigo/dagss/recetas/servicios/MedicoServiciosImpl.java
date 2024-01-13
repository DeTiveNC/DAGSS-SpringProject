package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class MedicoServiciosImpl implements MedicoServicios{
    @Autowired
    private CitaRepositorio citaRepositorio;
    @Autowired
    private MedicoRepositorio medicoRepositorio;
    @Autowired
    private PrescripcionRepositorio prescripcionRepositorio;
    @Autowired
    private RecetaRepositorio recetaRepositorio;
    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;
    @Override
    public List<Cita> devolverCitasMedico(String login) {
        return citaRepositorio.findCitasByMedico(medicoRepositorio.findMedicoByLogin(login).get());
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
    public Cita devolverCitaActual(String numColegiado) {
        LocalDate localDate = LocalDate.now();
        Date datenow = Date.valueOf(localDate);
        LocalTime localTime = LocalTime.now();
        Time timeNow = Time.valueOf(localTime);
        return citaRepositorio.findAppointmentByMedicoAndFechaAndHora(medicoRepositorio.findMedicoByNumeroColegiado(numColegiado).get(),datenow, timeNow);
    }
    @Override
    public Cita anularCita(String numTarjetaSanitaria,Cita cita) {
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
    public Prescripcion crearPrescripcionMedico(Medicamento medicamento, String numColegiado, String numTarjetaSanitaria, Double dosis, String indicaciones, Date fechFinPres, TipoEstado estado) {
        return null;
    }

    @Override
    public Medico viewMedico(String login) {
        return medicoRepositorio.findMedicoByLogin(login).get();
    }

    @Override
    public Medico editMedico(Medico editMedico) {
        return medicoRepositorio.save(editMedico);
    }
}
