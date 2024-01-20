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
    public List<Administrador> findAllAdmins(){
        return administradorRepositorio.findAll();
    }
    @Override
    public Administrador editAdministrador(Long id, Administrador editAdministrador){
        Optional<Administrador> administradorBusq = administradorRepositorio.findById(id);
        if (administradorBusq.isPresent() && administradorBusq.get().equals(editAdministrador)){
                return administradorRepositorio.save(editAdministrador);
        }
        return null;
    }
    @Override
    public Administrador deleteAdministrador(Long id){
        Optional<Administrador> administradorEliminado = administradorRepositorio.findById(id);
        if (administradorEliminado.isPresent()){
            administradorEliminado.get().desactivar();
            return administradorRepositorio.save(administradorEliminado.get());
        }
        return null;
    }
    @Override
    public Administrador newAdministrador(Administrador administrador){
        List<Administrador> findAllAdmins = administradorRepositorio.findAll();
        if (findAllAdmins.contains(administrador)) {
            return null;
        }
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
    public CentroDeSalud editCentroDeSalud(String nombreCentro, CentroDeSalud centroDeSalud){
        Optional<CentroDeSalud> centroDeSaludBusq = centroDeSaludRepositorio.findCentroDeSaludByNombre(nombreCentro);
        if (centroDeSaludBusq.isPresent() && centroDeSaludBusq.get().equals(centroDeSalud)){
                return centroDeSaludRepositorio.save(centroDeSalud);
        }
        return null;
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
        List<CentroDeSalud> findAllCentro = centroDeSaludRepositorio.findAll();
        if (findAllCentro.contains(centroDeSalud)) {
            return null;
        }
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
    public Medico editMedico(Long id, Medico medico){
        Optional<Medico> medicoBusq = medicoRepositorio.findById(id);
        if (medicoBusq.isPresent() && medicoBusq.get().equals(medico)){
                medicoRepositorio.save(medico);
        }
        return null;
    }

    @Override
    public Medico deleteMedico(Long id) {
        Optional<Medico> medicoOptional = medicoRepositorio.findById(id);
        if (medicoOptional.isPresent()){
            medicoOptional.get().desactivar();
            return medicoRepositorio.save(medicoOptional.get());
        }
        return null;
    }
    @Override
    public Medico newMedicoA(Medico medico){
        List<Medico> findAllMedico = medicoRepositorio.findAll();
        if (findAllMedico.contains(medico)) {
            return null;
        }
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
    public List<Paciente> findPacienteByCentroDeSaludyMedico(String centroDeSalud, Long medico){
        return pacienteRepositorio.findPacientesByCentroDeSaludAndMedico(centroDeSalud, medico);
    }
    @Override
    public Paciente editPacienteA(Long id, Paciente paciente){
        Optional<Paciente> pacienteBusq = pacienteRepositorio.findById(id);
        if (pacienteBusq.isPresent() && pacienteBusq.get().equals(paciente)){
            pacienteRepositorio.save(paciente);
        }
        return null;
    }
    @Override
    public Paciente deletePaciente(Long id){
        Optional<Paciente> pacienteBusq = pacienteRepositorio.findById(id);
        if (pacienteBusq.isPresent()){
            pacienteBusq.get().desactivar();
            return pacienteRepositorio.save(pacienteBusq.get());
        }
        return null;
    }
    @Override
    public Paciente newPaciente(Paciente paciente){
        List<Paciente> findAllPaciente = pacienteRepositorio.findAll();
        if (findAllPaciente.contains(paciente)) {
            return null;
        }
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
    public Farmacia editFarmaciaA(Long id, Farmacia editFarmacia){
        Optional<Farmacia> farmaciaBusq = farmaciaRepositorio.findById(id);
        if (farmaciaBusq.isPresent() && farmaciaBusq.get().equals(editFarmacia)){
            farmaciaRepositorio.save(editFarmacia);
        }
        return null;
    }
    @Override
    public Farmacia deleteFarmacia(Long id){
        Optional<Farmacia> optionalFarmacia = farmaciaRepositorio.findById(id);
        if (optionalFarmacia.isPresent()){
            optionalFarmacia.get().desactivar();
            return farmaciaRepositorio.save(optionalFarmacia.get());
        }
        return null;
    }
    @Override
    public Farmacia newFarmacia(Farmacia farmacia){
        List<Farmacia> findAllFarmacia = farmaciaRepositorio.findAll();
        if (findAllFarmacia.contains(farmacia)) {
            return null;
        }
        return farmaciaRepositorio.save(farmacia);
    }
    @Override
    public List<Cita> findAllCitas(){
        return citaRepositorio.findAll();
    }
    @Override
    public List<Cita> findCitasByFechayMedicooPaciente(String numColegiado, String numTarjetaSanitaria){
        return citaRepositorio.findCitasByMedicoAndPaciente(numColegiado,numTarjetaSanitaria);
    }
    @Override
    public Cita anularCitaA(Long id, Cita cita){
        Optional<Cita> citaBusq = citaRepositorio.findById(id);
        if (citaBusq.isPresent() && citaBusq.get().equals(cita)){
            return citaRepositorio.save(cita);
        }
        return null;
    }
    @Override
    public Cita deleteCita(Long id){
        Optional<Cita> citaOptional = citaRepositorio.findById(id);
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
    public Medicamento editMedicamento(String nombreComercial, Medicamento medicamento){
        Optional<Medicamento> medicamentoBusq = medicamentoRepositorio.findById(nombreComercial);
        if (medicamentoBusq.isPresent() && medicamentoBusq.get().equals(medicamento)){
            return medicamentoRepositorio.save(medicamento);
        }
        return null;
    }
    @Override
    public Medicamento deleteMedicamento(String nombreComercial){
        Optional<Medicamento> medicamentoOptional = medicamentoRepositorio.findById(nombreComercial);
        if (medicamentoOptional.isPresent()){
            medicamentoOptional.get().setActivo(false);
            return medicamentoRepositorio.save(medicamentoOptional.get());
        }
        return null;
    }
    @Override
    public Medicamento newMedicamento(Medicamento medicamento){
        List<Medicamento> findAllMedicamento = medicamentoRepositorio.findAll();
        if (findAllMedicamento.contains(medicamento)) {
            return null;
        }
        return medicamentoRepositorio.save(medicamento);
    }
}
