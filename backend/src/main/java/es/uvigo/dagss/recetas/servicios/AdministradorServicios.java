package es.uvigo.dagss.recetas.servicios;


import es.uvigo.dagss.recetas.entidades.*;

import java.sql.Date;
import java.util.List;

public interface AdministradorServicios {
    List<Administrador> findAllAdmins();
    Administrador editAdministrador(Administrador administrador);
    Administrador deleteAdministrador(String login);
    Administrador newAdministrador(Administrador administrador);
    List<CentroDeSalud> findAllCentros();
    List<CentroDeSalud> findCentrosByNombreoLocalidad(String nombre, String localidad);
    CentroDeSalud editCentroDeSalud(CentroDeSalud centroDeSalud);
    CentroDeSalud deleteCentroDeSalud(String login);
    CentroDeSalud newCentroDeSalud(CentroDeSalud centroDeSalud);
    List<Medico> findAllMedicos();
    List<Medico> findMedicosByNombreoLocalidad(String nombre, String localidad);
    Medico editMedico(Medico medico);
    Medico deleteMedico(String login);
    Medico newMedicoA(Medico medico);
    List<Paciente> findAllPacientes();
    List<Paciente> findPacienteByNombreoLocalidad(String nombre, String localidad);
    List<Paciente> findPacienteByCentroDeSaludyMedico(String nombre, String localidad);
    List<Paciente> findPacienteByCentroDeSaludyMedico(String centroDeSalud, Long medico);
    Paciente editPacienteA(Paciente paciente);
    Paciente deletePaciente(String login);
    Paciente newPaciente(Paciente paciente);
    List<Farmacia> findAllFarmacias();
    List<Farmacia> findFarmaciasByNombreoLocalidad(String nombre, String localidad);
    Farmacia editFarmaciaA(Farmacia farmacia);
    Farmacia deleteFarmacia(String login);
    Farmacia newFarmacia(Farmacia farmacia);
    List<Cita> findAllCitas();
    List<Cita> findCitasByFechayMedicooPaciente(String numColegiado, String numTarjetaSanitaria);
    Cita editCitaA(Cita cita);
    Cita deleteCita(Long login);
    List<Medicamento> findAllMedicamentos();
    List<Medicamento> findMedicamentoBy4(String valor, String valor2, String valor3, String valor4);
    Medicamento editFarmacia(Medicamento medicamento);
    Medicamento deleteMedicamento(String login);
    Medicamento newMedicamento(Medicamento medicamento);
}
