package es.uvigo.dagss.recetas.controlador;

import es.uvigo.dagss.recetas.dto.NewCitaPacienteDTO;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.servicios.PacienteServicios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/home/pacientes")
@Tag(name = "Paciente Endpoint", description = "Paciente Controlador")
public class PacienteControlador {

    @Autowired
    private PacienteServicios pacienteServicios;

    @GetMapping(path = "/citas/{numTarjetaSanitaria}")
    @Operation(summary = "Recuperar citas para un paciente específico",
            description = "Este endpoint recupera una lista de citas para el paciente especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de citas"),
            @ApiResponse(responseCode = "204", description = "No se encontraron citas para el paciente especificado")
    })
    public ResponseEntity<List<Cita>> devolverCitasPaciente(
            @PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria) {
        List<Cita> citasPaciente = pacienteServicios.devolverCitasPaciente(numTarjetaSanitaria);
        return citasPaciente.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(citasPaciente, HttpStatus.OK);
    }

    @PutMapping(path = "/citas/{numTarjetaSanitaria}")
    @Operation(summary = "Cancelar una cita para un paciente específico",
            description = "Este endpoint cancela una cita para un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita cancelada exitosamente"),
            @ApiResponse(responseCode = "204", description = "Error al cancelar la cita")
    })
    public ResponseEntity<Cita> anularCitaPaciente(
            @PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria, @RequestBody Cita cita) {
        Cita citaAnulada = pacienteServicios.anularCitaPaciente(numTarjetaSanitaria, cita);
        return citaAnulada != null
                ? new ResponseEntity<>(citaAnulada, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/citas/{id}")
    @Operation(summary = "Crear una nueva cita para un paciente específico",
            description = "Este endpoint crea una nueva cita para el paciente especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al crear la cita")
    })
    public ResponseEntity<Cita> crearCitaPaciente(
            @PathVariable("id") Long id, @RequestBody NewCitaPacienteDTO citaPaciente) {
        Cita newCitaPaciente = pacienteServicios.crearCitaPaciente(id,
                citaPaciente.getFecha(), citaPaciente.getHora());
        return newCitaPaciente != null
                ? new ResponseEntity<>(newCitaPaciente, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/recetas/{numTarjetaSanitaria}")
    @Operation(summary = "Recuperar recetas para un paciente específico",
            description = "Este endpoint recupera una lista de recetas para el paciente especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de recetas"),
            @ApiResponse(responseCode = "204", description = "No se encontraron recetas para el paciente especificado")
    })
    public ResponseEntity<List<Receta>> devolverRecetasPaciente(
            @PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria) {
        List<Receta> recetasPaciente = pacienteServicios.obtenerRecetasPaciente(numTarjetaSanitaria);
        return recetasPaciente.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(recetasPaciente, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/perfil")
    @Operation(summary = "Ver perfil del paciente",
            description = "Este endpoint recupera el perfil de un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa del perfil del paciente"),
            @ApiResponse(responseCode = "400", description = "Error al recuperar el perfil del paciente")
    })
    public ResponseEntity<Paciente> perfilPaciente(@PathVariable("id") Long id) {
        Paciente pacienteView = pacienteServicios.viewPaciente(id);
        return pacienteView != null
                ? new ResponseEntity<>(pacienteView, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(path = "/{id}/perfil")
    @Operation(summary = "Editar perfil del paciente",
            description = "Este endpoint permite editar el perfil de un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil de paciente editado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al editar el perfil del paciente")
    })
    public ResponseEntity<Paciente> perfilPaciente(
            @PathVariable("id") Long id, @RequestBody Paciente pacienteEditar) {
        Paciente pacienteEditado = pacienteServicios.editPaciente(id, pacienteEditar);
        return pacienteEditado != null
                ? new ResponseEntity<>(pacienteEditado, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
