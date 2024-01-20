package es.uvigo.dagss.recetas.controlador;


import es.uvigo.dagss.recetas.dto.CentroNLDTO;
import es.uvigo.dagss.recetas.dto.MedicoNLDTO;
import es.uvigo.dagss.recetas.entidades.Administrador;
import es.uvigo.dagss.recetas.entidades.CentroDeSalud;
import es.uvigo.dagss.recetas.entidades.Medico;
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
    public ResponseEntity<List<CentroDeSalud>> encontrarCentroNoL(@RequestBody CentroNLDTO centroNLDTO){
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
    public ResponseEntity<List<Medico>> encontrarMedicoNL(@RequestBody MedicoNLDTO medicoNLDTO){
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

}
