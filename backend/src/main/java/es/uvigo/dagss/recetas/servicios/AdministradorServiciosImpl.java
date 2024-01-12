package es.uvigo.dagss.recetas.servicios;

import java.sql.Date;
import java.util.List;

import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.repositorios.AdministradorRepositorio;
import es.uvigo.dagss.recetas.repositorios.MedicoRepositorio;
import es.uvigo.dagss.recetas.repositorios.PacienteRepositorio;

@Service
public class AdministradorServiciosImpl {
    @Autowired
    private AdministradorRepositorio administradorRepositorio;
    @Autowired
    private CentrosDeSaludRepositorio centroDeSaludRepositorio;
    @Autowired
    private MedicoRepositorio medicoRepositorio;
    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Override
    @Transactional(readOnly = true)
    public List<Administrador> findAllAdmins(){
        return administradorRepositorio.findAll();
    }

    @Override
    public Administrador editAdministrador(Administrador administrador){
        return administradorRepositorio.save(administrador);
    }
    @Override
    public Administrador deleteAdministrador(String login){
        return administradorRepositorio.delete(administradorRepositorio.findAdministradorByLogin(login));
    }
    @Override
    public Administrador newAdministrador(Administrador administrador){
        return administradorRepositorio.save(administrador);
    }
    @Override
    public List<CentroDeSalud> findAllCentros(){
        return centrosDeSaludRepositorio.findAll();
    }
    @Override
    public List<CentroDeSalud> findCentrosByNombreoLocalidad(String nombre, String localidad){
        return centrosDeSaludRepositorio.findCentroDeSaludsByNombreAndDireccion(nombre, localidad);
    }
    @Override
    public CentroDeSalud editCentroDeSalud(CentroDeSalud centroDeSalud){
        return centrosDeSaludRepositorio.save(centroDeSalud);
    }
    @Override
    public CentroDeSalud deleteCentroDeSalud(String nombre){
        return centrosDeSaludRepositorio.delete(centrosDeSaludRepositorio.findById(nombre));
    }
    @Override
    public CentroDeSalud newCentroDeSalud(CentroDeSalud centroDeSalud){
        return centrosDeSaludRepositorio.save(centroDeSalud);
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
    public Medico deleteMedico(Long login){
        return medicoRepositorio.delete(medicoRepositorio.findById(login));
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
    public List<Paciente> findPacienteByCentroDeSaludyMedico(String centroDeSalud, Long medico){
        return pacienteRepositorio.findPacientesByCentroDeSaludAndMedico(centroDeSalud, medico);
    }
    @Override
    public Paciente editPacienteA(Paciente paciente){

    }
    @Override
    public Paciente deletePaciente(String login){

    }
    @Override
    public Paciente newPaciente(Paciente paciente){

    }
    @Override
    public List<Farmacia> findAllFarmacias(){

    }
    @Override
    public List<Farmacia> findFarmaciasByNombreoLocalidad(String nombre, String localidad){

    }
    @Override
    public Farmacia editFarmaciaA(Farmacia farmacia){

    }
    @Override
    public Farmacia deleteFarmacia(String login){


    }
    @Override
    public Farmacia newFarmacia(Farmacia farmacia){

    }
    @Override
    public List<Cita> findAllCitas(){

    }
    @Override
    public List<Cita> findCitasByFechayMedicooPaciente(Date fecha, String numColegiado, String numTarjetaSanitaria){

    }
    @Override
    public Cita editCitaA(Cita cita){

    }
    @Override
    public Cita deleteCita(String login){

    }
    @Override
    public List<Medicamento> findAllMedicamentos(){

    }
    @Override
    public List<Medicamento> findMedicamentoBy4(String valor, String valor2, String valor3, String valor4){

    }
    @Override
    public Medicamento editFarmacia(Medicamento medicamento){

    }
    @Override
    public Medicamento deleteMedicamento(String login){

    }
    @Override
    public Medicamento newMedicamento(Medicamento medicamento){

    }
}
