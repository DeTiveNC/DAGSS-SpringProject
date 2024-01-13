package es.uvigo.dagss.recetas.servicios;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.repositorios.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServiciosImpl implements AdministradorServicios {
    @Autowired
    private AdministradorRepositorio administradorRepositorio;
    @Autowired
    private CentroDeSaludRepositorio centroDeSaludRepositorio;
    @Autowired
    private MedicoRepositorio medicoRepositorio;
    @Autowired
    private PacienteRepositorio pacienteRepositorio;
    @Autowired
    private FarmaciaRepositorio farmaciaRepositorio;
    @Autowired
    private CitaRepositorio citaRepositorio;
    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    @Override
    @Transactional
    public List<Administrador> findAllAdmins(){
        return administradorRepositorio.findAll();
    }

    @Override
    public Administrador editAdministrador(Administrador administrador){
        return administradorRepositorio.save(administrador);
    }
    @Override
    public Administrador deleteAdministrador(String login){
        Administrador administradorEliminado = administradorRepositorio.findAdministradorByLogin(login);
        if (administradorEliminado != null){
            administradorEliminado.setActivo(false);
            return administradorRepositorio.save(administradorEliminado);
        }
        return null;
    }
    @Override
    public Administrador newAdministrador(Administrador administrador){
        return administradorRepositorio.save(administrador);
    }
    @Override
    public List<CentroDeSalud> findAllCentros(){
        return centroDeSaludRepositorio.findAll();
    }
    @Override
    public List<CentroDeSalud> findCentrosByNombreoLocalidad(String nombre, String localidad){
        return centroDeSaludRepositorio.findCentroDeSaludsByNombreAndDireccion(nombre, localidad);
    }
    @Override
    public CentroDeSalud editCentroDeSalud(CentroDeSalud centroDeSalud){
        return centroDeSaludRepositorio.save(centroDeSalud);
    }
    @Override
    public CentroDeSalud deleteCentroDeSalud(String nombre){
        Optional<CentroDeSalud> centroDeSaludEliminado = centroDeSaludRepositorio.findCentroDeSaludByNombre(nombre);
        if (centroDeSaludEliminado.isPresent()){
            centroDeSaludEliminado.get().setActivo(false);
            return centroDeSaludRepositorio.save(centroDeSaludEliminado.get());
        }
        return null;
    }
    @Override
    public CentroDeSalud newCentroDeSalud(CentroDeSalud centroDeSalud){
        return centroDeSaludRepositorio.save(centroDeSalud);
    }
    @Override
    public List<Medico> findAllMedicos(){
        return medicoRepositorio.findAll();
    }
    @Override
    public List<Medico> findMedicosByNombreoLocalidad(String nombre, String localidad){
        return medicoRepositorio.findMedicosByNombreAndCentroDeSaludDireccionLocalidad(nombre, localidad);
    }
    @Override
    public Medico editMedico(Medico medico){
        return medicoRepositorio.save(medico);
    }

    @Override
    public Medico deleteMedico(String login) {
        Optional<Medico> medicoOptional = medicoRepositorio.findMedicoByLogin(login);
        if (medicoOptional.isPresent()){
            medicoOptional.get().setActivo(false);
            return medicoRepositorio.save(medicoOptional.get());
        }
        return null;
    }
    @Override
    public Medico newMedicoA(Medico medico){
        return medicoRepositorio.save(medico);
    }
    @Override
    public List<Paciente> findAllPacientes(){
        return pacienteRepositorio.findAll();

    }
    @Override
    public List<Paciente> findPacienteByNombreoLocalidad(String nombre, String localidad){
        return pacienteRepositorio.findPacientesByNombreAndLocalidad(nombre, localidad);
    }

    @Override
    public List<Paciente> findPacienteByCentroDeSaludyMedico(String nombre, String localidad) {
        return null;
    }

    @Override
    public List<Paciente> findPacienteByCentroDeSaludyMedico(String centroDeSalud, Long medico){
        return pacienteRepositorio.findPacientesByCentroDeSaludAndMedico(centroDeSalud, medico);
    }
    @Override
    public Paciente editPacienteA(Paciente paciente){
        return pacienteRepositorio.save(paciente);
    }
    @Override
    public Paciente deletePaciente(String login){
        Optional<Paciente> pacienteeliminado = pacienteRepositorio.findPacienteByLogin(login);
        if (pacienteeliminado.isPresent()){
            pacienteeliminado.get().setActivo(false);
            return pacienteRepositorio.save(pacienteeliminado.get());
        }
        return null;
    }
    @Override
    public Paciente newPaciente(Paciente paciente){
        return pacienteRepositorio.save(paciente);
    }
    @Override
    public List<Farmacia> findAllFarmacias(){
        return farmaciaRepositorio.findAll();
    }
    @Override
    public List<Farmacia> findFarmaciasByNombreoLocalidad(String nombre, String localidad){
        return farmaciaRepositorio.findFarmaciasByNombreEstablecimientoAndDireccionLocalidad(nombre,localidad);
    }
    @Override
    public Farmacia editFarmaciaA(Farmacia farmacia){
        return farmaciaRepositorio.save(farmacia);
    }
    @Override
    public Farmacia deleteFarmacia(String login){
        Optional<Farmacia> optionalFarmacia = farmaciaRepositorio.findFarmaciaByLogin(login);
        if (optionalFarmacia.isPresent()){
            optionalFarmacia.get().setActivo(false);
            return farmaciaRepositorio.save(optionalFarmacia.get());
        }
        return null;
    }
    @Override
    public Farmacia newFarmacia(Farmacia farmacia){
        return farmaciaRepositorio.save(farmacia);
    }
    @Override
    public List<Cita> findAllCitas(){
        return citaRepositorio.findAll();
    }
    @Override
    public List<Cita> findCitasByFechayMedicooPaciente(String numColegiado, String numTarjetaSanitaria){
        return citaRepositorio.findCitasByMedicoAndPaciente(medicoRepositorio.findMedicoByNumeroColegiado(numColegiado).get().getNombre(), pacienteRepositorio.findPacienteByNumTarjetaSanitaria(numTarjetaSanitaria).get().getNombre());
    }
    @Override
    public Cita editCitaA(Cita cita){
        return citaRepositorio.save(cita);
    }
    @Override
    public Cita deleteCita(Long login){
        Optional<Cita> citaOptional = citaRepositorio.findById(login);
        TipoEstado estadoCita = TipoEstado.ANULADA;
        if (citaOptional.isPresent()){
            citaOptional.get().setEstado(estadoCita);
            return citaRepositorio.save(citaOptional.get());
        }
        return null;
    }
    @Override
    public List<Medicamento> findAllMedicamentos(){
        return medicamentoRepositorio.findAll();
    }
    @Override
    public List<Medicamento> findMedicamentoBy4(String valor, String valor2, String valor3, String valor4){
        return medicamentoRepositorio.findMedicamentosByNombreComercialAndFabricanteAndFamiliaAndPrincipioActivo(valor,valor2,valor3,valor4);
    }
    @Override
    public Medicamento editFarmacia(Medicamento medicamento){
        return medicamentoRepositorio.save(medicamento);
    }
    @Override
    public Medicamento deleteMedicamento(String login){
        Optional<Medicamento> medicamentoOptional = medicamentoRepositorio.findById(login);
        if (medicamentoOptional.isPresent()){
            medicamentoOptional.get().setActivo(false);
            return medicamentoRepositorio.save(medicamentoOptional.get());
        }
        return null;
    }
    @Override
    public Medicamento newMedicamento(Medicamento medicamento){
        return medicamentoRepositorio.save(medicamento);
    }
}
