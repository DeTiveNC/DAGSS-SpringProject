package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.*;

import java.sql.Date;
import java.util.List;

public interface MedicoServicios {
    List<Cita> devolverCitasMedico(String login);
    Cita ausenciaPaciente(String numTarjetaSanitaria, Cita cita);
    Cita devolverCitaActual(String numColegiado);
    Cita anularCita(String numTarjetaSanitaria, Cita cita);
    Cita completarCita(String numTarjetaSanitaria, Cita cita);
    List<Medicamento> devolverMedicamentos(String valor1,String valor2,String valor3,String valor4);
    Prescripcion crearPrescripcionMedico(Medicamento medicamento, String numColegiado, String numTarjetaSanitaria, Double dosis, String indicaciones, Date fechFinPres, TipoEstado estado );
    Medico viewMedico(String login);
    Medico editMedico(Medico editMedico);
}
