package es.uvigo.dagss.recetas.servicios;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import es.uvigo.dagss.recetas.entidades.*;
import es.uvigo.dagss.recetas.daos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServiciosImpl implements AdministradorServicios {
    @Autowired
    private AdministradorDAO administradorDAO;
    @Autowired
    private CentroDeSaludDAO centroDeSaludDAO;
    @Autowired
    private MedicoDAO medicoDAO;
    @Autowired
    private PacienteDAO pacienteDAO;
    @Autowired
    private FarmaciaDAO farmaciaDAO;
    @Autowired
    private CitaDAO citaDAO;
    @Autowired
    private MedicamentoDAO medicamentoDAO;

    @Override
    public List<Administrador> findAllAdmins(){
        return administradorDAO.findAll();
    }
    @Override
    public Administrador editAdministrador(Long id, Administrador editAdministrador){
        Optional<Administrador> administradorBusq = administradorDAO.findById(id);
        if (administradorBusq.isPresent() && administradorBusq.get().getId().equals(editAdministrador.getId())){
            return administradorDAO.save(editAdministrador);
        }
        return null;
    }
    @Override
    public Administrador deleteAdministrador(Long id){
        Optional<Administrador> administradorEliminado = administradorDAO.findById(id);
        if (administradorEliminado.isPresent()){
            administradorEliminado.get().desactivar();
            return administradorDAO.save(administradorEliminado.get());
        }
        return null;
    }
    @Override
    public Administrador newAdministrador(Administrador administrador){
        List<Administrador> findAllAdmins = administradorDAO.findAll();
        if (findAllAdmins.contains(administrador)) {
            return null;
        }
        return administradorDAO.save(administrador);
    }
    @Override
    public List<CentroDeSalud> findAllCentros(){
        return centroDeSaludDAO.findAll();
    }
    @Override
    public List<CentroDeSalud> findCentrosByNombreoLocalidad(String nombre, String localidad){
        return centroDeSaludDAO.findCentroDeSaludsByNombreAndDireccion(nombre, localidad);
    }
    @Override
    public CentroDeSalud editCentroDeSalud(String nombreCentro, CentroDeSalud centroDeSalud){
        Optional<CentroDeSalud> centroDeSaludBusq = centroDeSaludDAO.findCentroDeSaludByNombre(nombreCentro);
        if (centroDeSaludBusq.isPresent() && centroDeSaludBusq.get().getNombre().equals(centroDeSalud.getNombre())){
            return centroDeSaludDAO.save(centroDeSalud);
        }
        return null;
    }
    @Override
    public CentroDeSalud deleteCentroDeSalud(String nombre){
        Optional<CentroDeSalud> centroDeSaludEliminado = centroDeSaludDAO.findCentroDeSaludByNombre(nombre);
        if (centroDeSaludEliminado.isPresent()){
            centroDeSaludEliminado.get().setActivo(false);
            return centroDeSaludDAO.save(centroDeSaludEliminado.get());
        }
        return null;
    }
    @Override
    public CentroDeSalud newCentroDeSalud(CentroDeSalud centroDeSalud){
        List<CentroDeSalud> findAllCentro = centroDeSaludDAO.findAll();
        if (findAllCentro.contains(centroDeSalud)) {
            return null;
        }
        return centroDeSaludDAO.save(centroDeSalud);
    }
    @Override
    public List<Medico> findAllMedicos(){
        return medicoDAO.findAll();
    }
    @Override
    public List<Medico> findMedicosByNombreoLocalidad(String nombre, String localidad){
        return medicoDAO.findMedicosByNombreAndCentroDeSaludDireccionLocalidad(nombre, localidad);
    }
    @Override
    public Medico editMedico(Long id, Medico medico){
        Optional<Medico> medicoBusq = medicoDAO.findById(id);
        if (medicoBusq.isPresent() && medicoBusq.get().getId().equals(medico.getId())){
            return medicoDAO.save(medico);
        }
        return null;
    }

    @Override
    public Medico deleteMedico(Long id) {
        Optional<Medico> medicoOptional = medicoDAO.findById(id);
        if (medicoOptional.isPresent()){
            medicoOptional.get().desactivar();
            return medicoDAO.save(medicoOptional.get());
        }
        return null;
    }
    @Override
    public Medico newMedicoA(Medico medico){
        List<Medico> findAllMedico = medicoDAO.findAll();
        if (findAllMedico.contains(medico)) {
            return null;
        }
        Random RMD = new Random();
        medico.setPassword(medico.getNumeroColegiado());
        List<CentroDeSalud> centroDeSaludRMD = centroDeSaludDAO.findAll();
        System.out.println(centroDeSaludRMD);
        if (!centroDeSaludRMD.isEmpty()){
            CentroDeSalud centroDeSaludInt = centroDeSaludRMD.get(RMD.nextInt(centroDeSaludRMD.size()));
            if (centroDeSaludInt != null){
                medico.setCentroDeSalud(centroDeSaludInt);
                return medicoDAO.save(medico);
            }
        }
        return null;
    }
    @Override
    public List<Paciente> findAllPacientes(){
        return pacienteDAO.findAll();

    }
    @Override
    public List<Paciente> findPacienteByNombreoLocalidad(String nombre, String localidad){
        return pacienteDAO.findPacientesByNombreAndLocalidad(nombre, localidad);
    }

    @Override
    public List<Paciente> findPacienteByCentroDeSaludyMedico(String centroDeSalud, Long medico){
        return pacienteDAO.findPacientesByCentroDeSaludAndMedico(centroDeSalud, medico);
    }
    @Override
    public Paciente editPacienteA(Long id, Paciente paciente){
        Optional<Paciente> pacienteBusq = pacienteDAO.findById(id);
        if (pacienteBusq.isPresent() && pacienteBusq.get().getId().equals(paciente.getId())){
            return pacienteDAO.save(paciente);
        }
        return null;
    }
    @Override
    public Paciente deletePaciente(Long id){
        Optional<Paciente> pacienteBusq = pacienteDAO.findById(id);
        if (pacienteBusq.isPresent()){
            pacienteBusq.get().desactivar();
            return pacienteDAO.save(pacienteBusq.get());
        }
        return null;
    }
    @Override
    public Paciente newPaciente(Paciente paciente){
        List<Paciente> findAllPaciente = pacienteDAO.findAll();
        if (findAllPaciente.contains(paciente)) {
            return null;
        }
        Random rmd = new Random();
        List<CentroDeSalud> centroDeSaludEscogido = centroDeSaludDAO.findCentroDeSaludsByNombreAndDireccionProvincia(null, paciente.getDireccion().getProvincia());
        if (!centroDeSaludEscogido.isEmpty()){
            CentroDeSalud CentroSaludRMD = centroDeSaludEscogido.get(rmd.nextInt(centroDeSaludEscogido.size()));
            List<Medico> medicoEscogido = medicoDAO.findMedicosByNombreAndCentroDeSaludDireccionProvincia(CentroSaludRMD.getDireccion().getProvincia(), null);
            if (!medicoEscogido.isEmpty()){
                paciente.setMedico(medicoEscogido.get(rmd.nextInt(medicoEscogido.size())));
                paciente.setCentroDeSalud(CentroSaludRMD);
                return pacienteDAO.save(paciente);
            }
        }
        return null;
    }
    @Override
    public List<Farmacia> findAllFarmacias(){
        return farmaciaDAO.findAll();
    }
    @Override
    public List<Farmacia> findFarmaciasByNombreoLocalidad(String nombre, String localidad){
        return farmaciaDAO.findFarmaciasByNombreEstablecimientoAndDireccionLocalidad(nombre,localidad);
    }
    @Override
    public Farmacia editFarmaciaA(Long id, Farmacia editFarmacia){
        Optional<Farmacia> farmaciaBusq = farmaciaDAO.findById(id);
        if (farmaciaBusq.isPresent() && farmaciaBusq.get().getId().equals(editFarmacia.getId())){
            return farmaciaDAO.save(editFarmacia);
        }
        return null;
    }
    @Override
    public Farmacia deleteFarmacia(Long id){
        Optional<Farmacia> optionalFarmacia = farmaciaDAO.findById(id);
        if (optionalFarmacia.isPresent()){
            optionalFarmacia.get().desactivar();
            return farmaciaDAO.save(optionalFarmacia.get());
        }
        return null;
    }
    @Override
    public Farmacia newFarmacia(Farmacia farmacia){
        List<Farmacia> findAllFarmacia = farmaciaDAO.findAll();
        if (findAllFarmacia.contains(farmacia)) {
            return null;
        }
        farmacia.setPassword(farmacia.getNumColegiado());
        return farmaciaDAO.save(farmacia);
    }
    @Override
    public List<Cita> findAllCitas(Date fecha){
        return citaDAO.findAppointmentsByFecha(fecha);
    }
    @Override
    public List<Cita> findCitasByFechayMedicooPaciente(String numColegiado, String numTarjetaSanitaria){
        return citaDAO.findCitasByMedicoAndPaciente(numColegiado,numTarjetaSanitaria);
    }
    @Override
    public Cita anularCitaA(Long id, Cita cita){
        Optional<Cita> citaBusq = citaDAO.findById(id);
        if (citaBusq.isPresent() && citaBusq.get().equals(cita)){
            return citaDAO.save(cita);
        }
        return null;
    }
    @Override
    public Cita deleteCita(Long id){
        Optional<Cita> citaOptional = citaDAO.findById(id);
        TipoEstado estadoCita = TipoEstado.ANULADA;
        if (citaOptional.isPresent()){
            citaOptional.get().setEstado(estadoCita);
            return citaDAO.save(citaOptional.get());
        }
        return null;
    }
    @Override
    public List<Medicamento> findAllMedicamentos(){
        return medicamentoDAO.findAll();
    }
    @Override
    public List<Medicamento> findMedicamentoBy4(String valor, String valor2, String valor3, String valor4){
        return medicamentoDAO.findMedicamentosByNombreComercialAndFabricanteAndFamiliaAndPrincipioActivo(valor,valor2,valor3,valor4);
    }
    @Override
    public Medicamento editMedicamento(String nombreComercial, Medicamento medicamento){
        Optional<Medicamento> medicamentoBusq = medicamentoDAO.findById(nombreComercial);
        if (medicamentoBusq.isPresent() && medicamentoBusq.get().getNombreComercial().equals(medicamento.getNombreComercial())){
            return medicamentoDAO.save(medicamento);
        }
        return null;
    }
    @Override
    public Medicamento deleteMedicamento(String nombreComercial){
        Optional<Medicamento> medicamentoOptional = medicamentoDAO.findById(nombreComercial);
        if (medicamentoOptional.isPresent()){
            medicamentoOptional.get().setActivo(false);
            return medicamentoDAO.save(medicamentoOptional.get());
        }
        return null;
    }
    @Override
    public Medicamento newMedicamento(Medicamento medicamento){
        List<Medicamento> findAllMedicamento = medicamentoDAO.findAll();
        if (findAllMedicamento.contains(medicamento)) {
            return null;
        }
        return medicamentoDAO.save(medicamento);
    }
}
