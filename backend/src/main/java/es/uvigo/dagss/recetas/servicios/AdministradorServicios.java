package es.uvigo.dagss.recetas.servicios;


import es.uvigo.dagss.recetas.entidades.*;

import java.sql.Date;
import java.util.List;

public interface AdministradorServicios {
    List<Administrador> findAllAdmins();
    Administrador editAdministrador(Long id, Administrador administrador);
    Administrador deleteAdministrador(Long id);
    Administrador newAdministrador(Administrador newAdministrador);
    List<CentroDeSalud> findAllCentros();
    List<CentroDeSalud> findCentrosByNombreoLocalidad(String nombre, String localidad);
    CentroDeSalud editCentroDeSalud(String nombreCentro, CentroDeSalud editcentroDeSalud);
    CentroDeSalud deleteCentroDeSalud(String nombreCentroDeSalud);
    CentroDeSalud newCentroDeSalud(CentroDeSalud newCentroDeSalud);
    List<Medico> findAllMedicos();
    List<Medico> findMedicosByNombreoLocalidad(String nombre, String localidad);
    Medico editMedico(Long id,Medico editMedico);
    Medico deleteMedico(Long id);
    Medico newMedicoA(Medico medico);
    List<Paciente> findAllPacientes();
    List<Paciente> findPacienteByNombreoLocalidad(String nombre, String localidad);
    List<Paciente> findPacienteByCentroDeSaludyMedico(String centroDeSalud, Long medico);
    Paciente editPacienteA(Long id,Paciente editPaciente);
    Paciente deletePaciente(Long id);
    Paciente newPaciente(Paciente newPaciente);
    List<Farmacia> findAllFarmacias();
    List<Farmacia> findFarmaciasByNombreoLocalidad(String nombre, String localidad);
    Farmacia editFarmaciaA(Long id, Farmacia editFarmacia);
    Farmacia deleteFarmacia(Long id);
    Farmacia newFarmacia(Farmacia newFarmacia);
    List<Cita> findAllCitas(Date fecha);
    List<Cita> findCitasByFechayMedicooPaciente(String numColegiado, String numTarjetaSanitaria);
    Cita anularCitaA(Long id, Cita editCita);
    Cita deleteCita(Long id);
    List<Medicamento> findAllMedicamentos();
    List<Medicamento> findMedicamentoBy4(String valor, String valor2, String valor3, String valor4);
    Medicamento editMedicamento(String nombreComercial, Medicamento editMedicamento);
    Medicamento deleteMedicamento(String nombreComercial);
    Medicamento newMedicamento(Medicamento newMedicamento);
}
