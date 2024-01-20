package es.uvigo.dagss.recetas.controlador;


import es.uvigo.dagss.recetas.dto.MedicamentoBusqDTO;
import es.uvigo.dagss.recetas.dto.NLSearchDTO;
import es.uvigo.dagss.recetas.dto.PMSearchDTO;
import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.servicios.AdministradorServicios;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/home/admins")
@Tag(name = "Admin Endpoint", description = "Admin Controlador")
public class AdminControlador {
    @Autowired
    private AdministradorServicios administradorServicios;
    @GetMapping(path = "/todosAdmins")
    public ResponseEntity<List<Administrador>> devolverAdmins(){
        List<Administrador> allAdmins = administradorServicios.findAllAdmins();
        return allAdmins.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(allAdmins, HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<Administrador> editarAdmin(@PathVariable("id") Long id, @RequestBody Administrador administradorEdit){
        Administrador editAdmin = administradorServicios.editAdministrador(id, administradorEdit);
        return editAdmin != null
                ? new ResponseEntity<>(editAdmin, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Administrador> eliminarAdmin(@PathVariable("id") Long id){
        Administrador administradorEliminado = administradorServicios.deleteAdministrador(id);
        return administradorEliminado != null
                ? new ResponseEntity<>(administradorEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/newAdministrador")
    public ResponseEntity<Administrador> nuevoAdministrador(@RequestBody Administrador administrador){
        Administrador nuevoAdministrador = administradorServicios.newAdministrador(administrador);
        return nuevoAdministrador != null
                ? new ResponseEntity<>(nuevoAdministrador, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping(path = "/centro/todosCentros")
    public ResponseEntity<List<CentroDeSalud>> encontrarTodosCentros(){
        List<CentroDeSalud> todosCentros = administradorServicios.findAllCentros();
        return todosCentros.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(todosCentros, HttpStatus.OK);
    }
    @GetMapping(path = "/centro/encontrarCentro")
    public ResponseEntity<List<CentroDeSalud>> encontrarCentroNoL(@ModelAttribute NLSearchDTO centroNLDTO){
        List<CentroDeSalud> centroDeSaludNL = administradorServicios.findCentrosByNombreoLocalidad(centroNLDTO.getNombre(), centroNLDTO.getLocalidad());
        return centroDeSaludNL.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(centroDeSaludNL, HttpStatus.OK);
    }
    @PutMapping(path = "/centro/{nombreCentro}")
    public ResponseEntity<CentroDeSalud> editarCentroSalud(@PathVariable("nombreCentro") String nombreCentro, @RequestBody CentroDeSalud centroDeSaludEdit){
        CentroDeSalud centroDeSaludEditado = administradorServicios.editCentroDeSalud(nombreCentro, centroDeSaludEdit);
        return centroDeSaludEditado != null
                ? new ResponseEntity<>(centroDeSaludEditado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/centro/{nombreCentro}")
    public ResponseEntity<CentroDeSalud> eliminarCentroSalud(@PathVariable("nombreCentro") String nombreCentro){
        CentroDeSalud centroDeSaludEliminado = administradorServicios.deleteCentroDeSalud(nombreCentro);
        return centroDeSaludEliminado != null
                ? new ResponseEntity<>(centroDeSaludEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping(path = "/newCentro")
    public ResponseEntity<CentroDeSalud> nuevoCentroSalud(@RequestBody CentroDeSalud centroDeSaludNuevo){
        CentroDeSalud nuevoCentroDeSalud = administradorServicios.newCentroDeSalud(centroDeSaludNuevo);
        return nuevoCentroDeSalud != null
                ? new ResponseEntity<>(nuevoCentroDeSalud, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping(path = "/medico/todoMedicos")
    public ResponseEntity<List<Medico>> encontrarTodosMedicos(){
        List<Medico> todosMedicos = administradorServicios.findAllMedicos();
        return todosMedicos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(todosMedicos, HttpStatus.OK);
    }
    @GetMapping(path = "/medico/medicosNL")
    public ResponseEntity<List<Medico>> encontrarMedicoNL(@ModelAttribute NLSearchDTO medicoNLDTO){
        List<Medico> medicoEncontrado = administradorServicios.findMedicosByNombreoLocalidad(medicoNLDTO.getNombre(), medicoNLDTO.getLocalidad());
        return medicoEncontrado.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicoEncontrado,HttpStatus.OK);
    }
    @PutMapping(path = "/medico/{id}")
    public ResponseEntity<Medico> editarMedico(@PathVariable("id") Long id,@RequestBody Medico medicoEdit){
        Medico editMedico = administradorServicios.editMedico(id, medicoEdit);
        return editMedico != null
                ? new ResponseEntity<>(editMedico, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/medico/{id}")
    public ResponseEntity<Medico> eliminarMedico(@PathVariable Long id){
        Medico medicoEliminado = administradorServicios.deleteMedico(id);
        return medicoEliminado != null
                ? new ResponseEntity<>(medicoEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/newMedico")
    public ResponseEntity<Medico> nuevoMedico(@RequestBody Medico medicoNuevo){
        Medico nuevoMedico = administradorServicios.newMedicoA(medicoNuevo);
        return nuevoMedico != null
                ? new ResponseEntity<>(nuevoMedico, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } 
    @GetMapping(path = "/paciente/todoPaciente")
    public ResponseEntity<List<Paciente>> encontrarTodosPaciente(){
        List<Paciente> todosPacientes = administradorServicios.findAllPacientes();
        return todosPacientes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(todosPacientes, HttpStatus.OK);
    }
    @GetMapping(path = "/paciente/pacienteNL")
    public ResponseEntity<List<Paciente>> encontrarpacienteNL(@ModelAttribute NLSearchDTO pacienteNLDTO){
        List<Paciente> pacienteEncontrado = administradorServicios.findPacienteByNombreoLocalidad(pacienteNLDTO.getNombre(), pacienteNLDTO.getLocalidad());
        return pacienteEncontrado.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(pacienteEncontrado,HttpStatus.OK);
    }
    @PutMapping(path = "/paciente/{id}")
    public ResponseEntity<Paciente> editarPaciente(@PathVariable("id") Long id,@RequestBody Paciente pacienteEdit){
        Paciente editPaciente = administradorServicios.editPacienteA(id, pacienteEdit);
        return editPaciente != null
                ? new ResponseEntity<>(editPaciente, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/paciente/{id}")
    public ResponseEntity<Paciente> eliminarPaciente(@PathVariable Long id){
        Paciente pacienteEliminado = administradorServicios.deletePaciente(id);
        return pacienteEliminado != null
                ? new ResponseEntity<>(pacienteEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/newPaciente")
    public ResponseEntity<Paciente> nuevoPaciente(@RequestBody Paciente pacienteNuevo){
        Paciente nuevoPaciente = administradorServicios.newPaciente(pacienteNuevo);
        return nuevoPaciente != null
                ? new ResponseEntity<>(nuevoPaciente, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } 
    @GetMapping(path = "/farmacia/todoFarmacia")
    public ResponseEntity<List<Farmacia>> encontrarTodosFarmacia(){
        List<Farmacia> todosFarmacias = administradorServicios.findAllFarmacias();
        return todosFarmacias.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(todosFarmacias, HttpStatus.OK);
    }
    @GetMapping(path = "/farmacia/farmaciaNL")
    public ResponseEntity<List<Farmacia>> encontrarfarmaciaNL(@ModelAttribute NLSearchDTO farmaciaNLDTO){
        List<Farmacia> farmaciaEncontrado = administradorServicios.findFarmaciasByNombreoLocalidad(farmaciaNLDTO.getNombre(), farmaciaNLDTO.getLocalidad());
        return farmaciaEncontrado.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(farmaciaEncontrado,HttpStatus.OK);
    }
    @PutMapping(path = "/farmacia/{id}")
    public ResponseEntity<Farmacia> editarFarmacia(@PathVariable("id") Long id,@RequestBody Farmacia farmaciaEdit){
        Farmacia editFarmacia = administradorServicios.editFarmaciaA(id, farmaciaEdit);
        return editFarmacia != null
                ? new ResponseEntity<>(editFarmacia, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/farmacia/{id}")
    public ResponseEntity<Farmacia> eliminarFarmacia(@PathVariable Long id){
        Farmacia farmaciaEliminado = administradorServicios.deleteFarmacia(id);
        return farmaciaEliminado != null
                ? new ResponseEntity<>(farmaciaEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/newFarmacia")
    public ResponseEntity<Farmacia> nuevoFarmacia(@RequestBody Farmacia farmaciaNuevo){
        Farmacia nuevoFarmacia = administradorServicios.newFarmacia(farmaciaNuevo);
        return nuevoFarmacia != null
                ? new ResponseEntity<>(nuevoFarmacia, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } 
    @GetMapping(path = "/cita/{fecha}")
    public ResponseEntity<List<Cita>> encontrarCitasPorFecha(@PathVariable String fecha){
        List<Cita> citasFecha = administradorServicios.findAllCitas(); //WIP
        return citasFecha.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(citasFecha, HttpStatus.OK);
    }
    @DeleteMapping(path = "/cita/{id}")
    public ResponseEntity<Cita> anularCita(@PathVariable long id){
        Cita citasFecha = administradorServicios.deleteCita(id);
        return citasFecha == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(citasFecha, HttpStatus.OK);
    }
    @GetMapping(path = "/cita/PacienteyMedico")
    public ResponseEntity<List<Cita>> encontrarCitasPorMedicoyPaciente(@ModelAttribute PMSearchDTO pmSearchDTO){
        List<Cita> citasPacienteYMedico = administradorServicios.findCitasByFechayMedicooPaciente(pmSearchDTO.getNumColegiado()
        , pmSearchDTO.getNumTarjetaSanitaria());
        return citasPacienteYMedico.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(citasPacienteYMedico, HttpStatus.OK);
    }

    @GetMapping(path = "/medicamento/todoMedicamentos")
    public ResponseEntity<List<Medicamento>> encontrarMedicamentos(){
        List<Medicamento> medicamentos = administradorServicios.findAllMedicamentos();
        return medicamentos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }
    @GetMapping(path = "/medicamento/filtrado")
    public ResponseEntity<List<Medicamento>> filtrarMedicamentos(@ModelAttribute MedicamentoBusqDTO medicamentFilterDTO){
        List<Medicamento> medicamentos = administradorServicios.findMedicamentoBy4(medicamentFilterDTO.getNombreComercial(),
        medicamentFilterDTO.getPrincipioActivo(),
        medicamentFilterDTO.getFabricante(),
        medicamentFilterDTO.getFamilia());
        return medicamentos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }
    @PutMapping(path = "/medicamento/{nombrecomercial}")
    public ResponseEntity<Medicamento> editarMedicamentos(@PathVariable String nombrecomercial,@RequestBody Medicamento medicamentEdit){
        Medicamento medicamentos = administradorServicios.editMedicamento(nombrecomercial, medicamentEdit);
        return medicamentos == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }
    @DeleteMapping(path = "/medicamento/{nombrecomercial}")
    public ResponseEntity<Medicamento> deleteMedicamentos(@PathVariable String nombrecomercial){
        Medicamento medicamentos = administradorServicios.deleteMedicamento(nombrecomercial);
        return medicamentos == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }

    @PostMapping(path = "/newMedicamento")
    public ResponseEntity<Medicamento> nuevoMedicamento(@RequestBody Medicamento medicamentoNuevo){
        Medicamento nuevoMedicamento = administradorServicios.newMedicamento(medicamentoNuevo);
        return nuevoMedicamento != null
                ? new ResponseEntity<>(nuevoMedicamento, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } 

}
