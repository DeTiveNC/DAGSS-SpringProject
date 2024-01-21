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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/home/admins")
@Tag(name = "Admin Endpoint", description = "Admin Controlador")
public class AdminControlador {
    @Autowired
    private AdministradorServicios administradorServicios;
    @GetMapping(path = "/todosAdmins")
    @Operation(summary = "Lista de todos los admins",
            description = "Este endpoint lista todos los admins.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de los admins."),
            @ApiResponse(responseCode = "204", description = "Error al recuperar los admins.")
    })
    public ResponseEntity<List<Administrador>> devolverAdmins(){
        List<Administrador> allAdmins = administradorServicios.findAllAdmins();
        return allAdmins.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(allAdmins, HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    @Operation(summary = "Editar un admin",
            description = "Este endpoint edita el perfil de un admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edición de un admin exitosa"),
            @ApiResponse(responseCode = "400", description = "Error al editar un admin")
    })
    public ResponseEntity<Administrador> editarAdmin(@PathVariable("id") Long id, @RequestBody Administrador administradorEdit){
        Administrador editAdmin = administradorServicios.editAdministrador(id, administradorEdit);
        return editAdmin != null
                ? new ResponseEntity<>(editAdmin, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Eliminar perfil de Admin",
            description = "Este endpoint elimina un admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminación de un admin"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar un admin")
    })
    public ResponseEntity<Administrador> eliminarAdmin(@PathVariable("id") Long id){
        Administrador administradorEliminado = administradorServicios.deleteAdministrador(id);
        return administradorEliminado != null
                ? new ResponseEntity<>(administradorEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/administrador")
    @Operation(summary = "Crear un nuevo Admin",
            description = "Este endpoint crea un nuevo admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creación exitosa de un admin"),
            @ApiResponse(responseCode = "400", description = "Error al crear un admin")
    })
    public ResponseEntity<Administrador> nuevoAdministrador(@RequestBody Administrador administrador){
        Administrador nuevoAdministrador = administradorServicios.newAdministrador(administrador);
        return nuevoAdministrador != null
                ? new ResponseEntity<>(nuevoAdministrador, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping(path = "/centro/todosCentros")
    @Operation(summary = "Lista de todos los centros",
            description = "Este endpoint recupera todos los centros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de todos los centros"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar los centros")
    })
    public ResponseEntity<List<CentroDeSalud>> encontrarTodosCentros(){
        List<CentroDeSalud> todosCentros = administradorServicios.findAllCentros();
        return todosCentros.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(todosCentros, HttpStatus.OK);
    }
    @GetMapping(path = "/centro/filtrarCentro")
    @Operation(summary = "Encontrar centros",
            description = "Este endpoint encuentra un centro según nombre y localidad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa del perfil del médico"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar el perfil del médico")
    })
    public ResponseEntity<List<CentroDeSalud>> encontrarCentroNoL(@ModelAttribute NLSearchDTO centroNLDTO){
        List<CentroDeSalud> centroDeSaludNL = administradorServicios.findCentrosByNombreoLocalidad(centroNLDTO.getNombre(), centroNLDTO.getLocalidad());
        return centroDeSaludNL.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(centroDeSaludNL, HttpStatus.OK);
    }
    @PutMapping(path = "/centro/{nombreCentro}")
    @Operation(summary = "Editar un centro",
            description = "Este endpoint edita el perfil de un centro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edición exitosa del perfil de un centro"),
            @ApiResponse(responseCode = "400", description = "Error al editar el perfil de un centro")
    })
    public ResponseEntity<CentroDeSalud> editarCentroSalud(@PathVariable("nombreCentro") String nombreCentro, @RequestBody CentroDeSalud centroDeSaludEdit){
        CentroDeSalud centroDeSaludEditado = administradorServicios.editCentroDeSalud(nombreCentro, centroDeSaludEdit);
        return centroDeSaludEditado != null
                ? new ResponseEntity<>(centroDeSaludEditado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/centro/{nombreCentro}")
    @Operation(summary = "Eliminar un centro",
            description = "Este endpoint elimina un centro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminación exitosa de un centro"),
            @ApiResponse(responseCode = "204", description = "Error al eliminar un centro")
    })
    public ResponseEntity<CentroDeSalud> eliminarCentroSalud(@PathVariable("nombreCentro") String nombreCentro){
        CentroDeSalud centroDeSaludEliminado = administradorServicios.deleteCentroDeSalud(nombreCentro);
        return centroDeSaludEliminado != null
                ? new ResponseEntity<>(centroDeSaludEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping(path = "/centro")
    @Operation(summary = "Crear un nuevo Centro",
            description = "Este endpoint crea un nuevo centro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creación exitosa de un centro"),
            @ApiResponse(responseCode = "400", description = "Error al crear un centro")
    })
    public ResponseEntity<CentroDeSalud> nuevoCentroSalud(@RequestBody CentroDeSalud centroDeSaludNuevo){
        CentroDeSalud nuevoCentroDeSalud = administradorServicios.newCentroDeSalud(centroDeSaludNuevo);
        return nuevoCentroDeSalud != null
                ? new ResponseEntity<>(nuevoCentroDeSalud, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @GetMapping(path = "/medico/todoMedicos")
    @Operation(summary = "Listar medicos",
            description = "Este endpoint lista el perfil de los médicos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de los perfiles de los médicos"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar los perfiles de los médicos")
    })
    public ResponseEntity<List<Medico>> encontrarTodosMedicos(){
        List<Medico> todosMedicos = administradorServicios.findAllMedicos();
        return todosMedicos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(todosMedicos, HttpStatus.OK);
    }
    @GetMapping(path = "/medico/filtrarMedico")
    @Operation(summary = "Encontrar medicos segun parametros",
            description = "Este endpoint recupera el perfil de un médico segun nombre o localidad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa del perfil del médico"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar el perfil del médico")
    })
    public ResponseEntity<List<Medico>> encontrarMedicoNL(@ModelAttribute NLSearchDTO medicoNLDTO){
        List<Medico> medicoEncontrado = administradorServicios.findMedicosByNombreoLocalidad(medicoNLDTO.getNombre(), medicoNLDTO.getLocalidad());
        return medicoEncontrado.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicoEncontrado,HttpStatus.OK);
    }
    @PutMapping(path = "/medico/{id}")
    @Operation(summary = "Editar perfil de medico",
            description = "Este endpoint edita el perfil de un médico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edición exitosa de un perfil del medico"),
            @ApiResponse(responseCode = "400", description = "Error al editar el perfil del médico")
    })
    public ResponseEntity<Medico> editarMedico(@PathVariable("id") Long id,@RequestBody Medico medicoEdit){
        Medico editMedico = administradorServicios.editMedico(id, medicoEdit);
        return editMedico != null
                ? new ResponseEntity<>(editMedico, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/medico/{id}")
    @Operation(summary = "Eliminar perfil de medico",
            description = "Este endpoint elimina el perfil de un médico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminación exitosa del perfil del médico"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar el perfil del médico")
    })
    public ResponseEntity<Medico> eliminarMedico(@PathVariable Long id){
        Medico medicoEliminado = administradorServicios.deleteMedico(id);
        return medicoEliminado != null
                ? new ResponseEntity<>(medicoEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/medico")
    @Operation(summary = "Crear un nuevo Medico",
            description = "Este endpoint crea un nuevo medico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creación exitosa de un medico"),
            @ApiResponse(responseCode = "400", description = "Error al crear un medico")
    })
    public ResponseEntity<Medico> nuevoMedico(@RequestBody Medico medicoNuevo){
        Medico nuevoMedico = administradorServicios.newMedicoA(medicoNuevo);
        return nuevoMedico != null
                ? new ResponseEntity<>(nuevoMedico, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } 
    @GetMapping(path = "/paciente/todoPaciente")
    @Operation(summary = "Lista de todos los pacientes",
            description = "Este endpoint lista el perfil de todos los pacientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de los perfiles de los médicos"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar todos los perfiles de los medicos")
    })
    public ResponseEntity<List<Paciente>> encontrarTodosPaciente(){
        List<Paciente> todosPacientes = administradorServicios.findAllPacientes();
        return todosPacientes.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(todosPacientes, HttpStatus.OK);
    }
    @GetMapping(path = "/paciente/filtrarPaciente")
    @Operation(summary = "Ver perfil de los pacientes segun parametros",
            description = "Este endpoint recupera el perfil de un paciente segun Nombre o Localidad.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa del perfil del paciente"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar el perfil del paciente")
    })
    public ResponseEntity<List<Paciente>> encontrarpacienteNL(@ModelAttribute NLSearchDTO pacienteNLDTO){
        List<Paciente> pacienteEncontrado = administradorServicios.findPacienteByNombreoLocalidad(pacienteNLDTO.getNombre(), pacienteNLDTO.getLocalidad());
        return pacienteEncontrado.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(pacienteEncontrado,HttpStatus.OK);
    }
    @PutMapping(path = "/paciente/{id}")
    @Operation(summary = "Editar perfil del paciente",
            description = "Este endpoint edita el perfil de un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edición exitosa del perfil del paciente"),
            @ApiResponse(responseCode = "400", description = "Error al editar el perfil del paciente")
    })
    public ResponseEntity<Paciente> editarPaciente(@PathVariable("id") Long id,@RequestBody Paciente pacienteEdit){
        Paciente editPaciente = administradorServicios.editPacienteA(id, pacienteEdit);
        return editPaciente != null
                ? new ResponseEntity<>(editPaciente, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/paciente/{id}")
    @Operation(summary = "Eliminación del perfil de un paciente",
            description = "Este endpoint elimina el perfil de un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminación exitosa del perfil de un paciente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar el perfil de un paciente")
    })
    public ResponseEntity<Paciente> eliminarPaciente(@PathVariable Long id){
        Paciente pacienteEliminado = administradorServicios.deletePaciente(id);
        return pacienteEliminado != null
                ? new ResponseEntity<>(pacienteEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/paciente")
    @Operation(summary = "Crear un nuevo paciente",
            description = "Este endpoint crea un nuevo paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creación exitosa de un paciente"),
            @ApiResponse(responseCode = "400", description = "Error al crear un paciente")
    })
    public ResponseEntity<Paciente> nuevoPaciente(@RequestBody Paciente pacienteNuevo){
        Paciente nuevoPaciente = administradorServicios.newPaciente(pacienteNuevo);
        return nuevoPaciente != null
                ? new ResponseEntity<>(nuevoPaciente, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } 
    @GetMapping(path = "/farmacia/todoFarmacia")
    @Operation(summary = "Ver todos los perfiles de las farmacias",
            description = "Este endpoint lista el perfil de las farmacias.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa del perfil de las farmacias"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar el perfil de las farmacias")
    })
    public ResponseEntity<List<Farmacia>> encontrarTodosFarmacia(){
        List<Farmacia> todosFarmacias = administradorServicios.findAllFarmacias();
        return todosFarmacias.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(todosFarmacias, HttpStatus.OK);
    }
    @GetMapping(path = "/farmacia/filtrarFarmacia")
    @Operation(summary = "Ver perfil de las farmacias",
            description = "Este endpoint recupera el perfil de las farmacias.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa del perfil de las farmacias"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar el perfil de las farmacias")
    })
    public ResponseEntity<List<Farmacia>> encontrarfarmaciaNL(@ModelAttribute NLSearchDTO farmaciaNLDTO){
        List<Farmacia> farmaciaEncontrado = administradorServicios.findFarmaciasByNombreoLocalidad(farmaciaNLDTO.getNombre(), farmaciaNLDTO.getLocalidad());
        return farmaciaEncontrado.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(farmaciaEncontrado,HttpStatus.OK);
    }
    @PutMapping(path = "/farmacia/{id}")
    @Operation(summary = "Editar perfil de farmacia",
            description = "Este endpoint edita el perfil de una farmacia.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edicion exitosa del perfil de una farmacia"),
            @ApiResponse(responseCode = "400", description = "Error al editar el perfil de una farmacia")
    })
    public ResponseEntity<Farmacia> editarFarmacia(@PathVariable("id") Long id,@RequestBody Farmacia farmaciaEdit){
        Farmacia editFarmacia = administradorServicios.editFarmaciaA(id, farmaciaEdit);
        return editFarmacia != null
                ? new ResponseEntity<>(editFarmacia, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping(path = "/farmacia/{id}")
    @Operation(summary = "Eliminar perfil de farmacia",
            description = "Este endpoint elimina el perfil de una farmacia.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminacion exitosa del perfil de farmacia"),
            @ApiResponse(responseCode = "400", description = "Error al aliminar el perfil de farmacia")
    })
    public ResponseEntity<Farmacia> eliminarFarmacia(@PathVariable Long id){
        Farmacia farmaciaEliminado = administradorServicios.deleteFarmacia(id);
        return farmaciaEliminado != null
                ? new ResponseEntity<>(farmaciaEliminado, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PostMapping(path = "/farmacia")
    @Operation(summary = "Crear una nueva farmacia",
            description = "Este endpoint crea una nueva farmacia.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creación exitosa de farmacia"),
            @ApiResponse(responseCode = "400", description = "Error al crear una farmacia")
    })
    public ResponseEntity<Farmacia> nuevoFarmacia(@RequestBody Farmacia farmaciaNuevo){
        Farmacia nuevoFarmacia = administradorServicios.newFarmacia(farmaciaNuevo);
        return nuevoFarmacia != null
                ? new ResponseEntity<>(nuevoFarmacia, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } 
    @GetMapping(path = "/cita/{fecha}")
    @Operation(summary = "Ver citas segun fecha",
            description = "Este endpoint recupera citas segun fecha.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de las citas"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar las citas")
    })
    public ResponseEntity<List<Cita>> encontrarCitasPorFecha(@PathVariable Date fecha){
        List<Cita> citasFecha = administradorServicios.findAllCitas(fecha);
        return citasFecha.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(citasFecha, HttpStatus.OK);
    }
    @DeleteMapping(path = "/cita/{id}")
    @Operation(summary = "Anular cita",
            description = "Este endpoint anula una cita.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anulacion exitosa de una cita"),
            @ApiResponse(responseCode = "204", description = "Error al anular una cita")
    })
    public ResponseEntity<Cita> anularCita(@PathVariable long id){
        Cita citasFecha = administradorServicios.deleteCita(id);
        return citasFecha == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(citasFecha, HttpStatus.OK);
    }
    @GetMapping(path = "/cita/filtrarPorPacienteyMedico")
    @Operation(summary = "Ver cita segun parametros",
            description = "Este endpoint recupera una cita segun paciente o medico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de una cita"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar una cita")
    })
    public ResponseEntity<List<Cita>> encontrarCitasPorMedicoyPaciente(@ModelAttribute PMSearchDTO pmSearchDTO){
        List<Cita> citasPacienteYMedico = administradorServicios.findCitasByFechayMedicooPaciente(pmSearchDTO.getNumColegiado()
        , pmSearchDTO.getNumTarjetaSanitaria());
        return citasPacienteYMedico.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(citasPacienteYMedico, HttpStatus.OK);
    }

    @GetMapping(path = "/medicamento/todoMedicamentos")
    @Operation(summary = "Ver todos los medicamentos",
            description = "Este endpoint recupera todos los medicamentos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de los medicamentos"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar los medicamentos")
    })
    public ResponseEntity<List<Medicamento>> encontrarMedicamentos(){
        List<Medicamento> medicamentos = administradorServicios.findAllMedicamentos();
        return medicamentos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }
    @GetMapping(path = "/medicamento/filtrado")
    @Operation(summary = "Ver medicamentos segun filtrado",
            description = "Este endpoint recupera medicamentos segun familia, principio activo, fabricante o nombre comercial.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de los medicamentos"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar los medicamentos")
    })
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
    @Operation(summary = "Editar medicamento",
            description = "Este endpoint edita un medicamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edicion exitosa de un medicamento"),
            @ApiResponse(responseCode = "204", description = "Error al editar un medicamento")
    })
    public ResponseEntity<Medicamento> editarMedicamentos(@PathVariable String nombrecomercial,@RequestBody Medicamento medicamentEdit){
        Medicamento medicamentos = administradorServicios.editMedicamento(nombrecomercial, medicamentEdit);
        return medicamentos == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }
    @DeleteMapping(path = "/medicamento/{nombrecomercial}")
    @Operation(summary = "Eliminar un medicamento",
            description = "Este endpoint elimina un medicamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminacion exitosa de un medicamento"),
            @ApiResponse(responseCode = "204", description = "Error al eliminar un medicamento")
    })
    public ResponseEntity<Medicamento> deleteMedicamentos(@PathVariable String nombrecomercial){
        Medicamento medicamentos = administradorServicios.deleteMedicamento(nombrecomercial);
        return medicamentos == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }

    @PostMapping(path = "/medicamento")
    @Operation(summary = "Crear un nuevo medicamento",
            description = "Este endpoint crea un nuevo medicamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creación exitosa de un medicamento"),
            @ApiResponse(responseCode = "400", description = "Error al crear un medicamento")
    })
    public ResponseEntity<Medicamento> nuevoMedicamento(@RequestBody Medicamento medicamentoNuevo){
        Medicamento nuevoMedicamento = administradorServicios.newMedicamento(medicamentoNuevo);
        return nuevoMedicamento != null
                ? new ResponseEntity<>(nuevoMedicamento, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } 

}
