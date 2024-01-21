package es.uvigo.dagss.recetas.controlador;

import es.uvigo.dagss.recetas.dto.CrearPrescripcionDTO;
import es.uvigo.dagss.recetas.dto.MedicamentoBusqDTO;
import es.uvigo.dagss.recetas.dto.MedicoCitaDTO;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Medicamento;
import es.uvigo.dagss.recetas.entidades.Medico;
import es.uvigo.dagss.recetas.entidades.Prescripcion;
import es.uvigo.dagss.recetas.servicios.MedicoServicios;
import es.uvigo.dagss.recetas.servicios.PacienteServicios;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/home/medicos")
@Tag(name = "Medico Endpoint", description = "Medico Controlador")
public class MedicoControlador {
    @Autowired
    private PacienteServicios pacienteServicios;
    @Autowired
    private MedicoServicios medicoServicios;

    @GetMapping(path = "/citas/{login}")
    @Operation(summary = "Devolver citas de un médico",
            description = "Este endpoint devuelve la lista de citas para el médico especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de citas"),
            @ApiResponse(responseCode = "204", description = "No se encontraron citas para el médico especificado")
    })
    public ResponseEntity<List<Cita>> devolverCitasMedico(
            @PathVariable("login") String login) {
        List<Cita> citasDispo = medicoServicios.devolverCitasMedico(login);
        return citasDispo.isEmpty()
                ? new ResponseEntity<>(citasDispo,HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(citasDispo, HttpStatus.OK);
    }

    @PutMapping(path = "/citas/{numTarjetaSanitaria}")
    @Operation(summary = "Registrar ausencia de un paciente",
            description = "Este endpoint registra la ausencia de un paciente para el médico especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ausencia registrada exitosamente"),
            @ApiResponse(responseCode = "204", description = "Error al registrar la ausencia")
    })
    public ResponseEntity<Cita> ausenciaPaciente(
            @PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria, @RequestBody Cita cita) {
        Cita citaAusencia = medicoServicios.ausenciaPaciente(numTarjetaSanitaria, cita);
        return citaAusencia != null
                ? new ResponseEntity<>(citaAusencia, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/cita-actual/{numColegiado}")
    @Operation(summary = "Devolver cita actual del médico",
            description = "Este endpoint devuelve la cita actual para el médico y paciente especificados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de la cita actual"),
            @ApiResponse(responseCode = "204", description = "No se encontró cita actual para el médico y paciente especificados")
    })
    public ResponseEntity<Cita> devolverCitaActual(
            @PathVariable("numColegiado") String numColegiado, @Valid @RequestBody MedicoCitaDTO citaMedicoActual) {
        Cita citaActual = medicoServicios.devolverCitaActual(numColegiado, citaMedicoActual.getFecha(), citaMedicoActual.getHora());
        return citaActual != null
                ? new ResponseEntity<>(citaActual, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/anular/{numTarjetaSanitaria}")
    @Operation(summary = "Anular cita de un paciente",
            description = "Este endpoint anula una cita para el paciente especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita anulada exitosamente"),
            @ApiResponse(responseCode = "204", description = "Error al anular la cita")
    })
    public ResponseEntity<Cita> anularCita(
            @PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria, @RequestBody Cita cita) {
        Cita citaAnular = medicoServicios.anularCita(numTarjetaSanitaria, cita);
        return citaAnular != null
                ? new ResponseEntity<>(citaAnular, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/completar/{numTarjetaSanitaria}")
    @Operation(summary = "Completar cita de un paciente",
            description = "Este endpoint marca una cita como completada para el paciente especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cita completada exitosamente"),
            @ApiResponse(responseCode = "204", description = "Error al completar la cita")
    })
    public ResponseEntity<Cita> completarCita(@PathVariable String numTarjetaSanitaria, @RequestBody Cita cita) {
        Cita completarCita = medicoServicios.completarCita(numTarjetaSanitaria, cita);
        return completarCita != null
                ? new ResponseEntity<>(completarCita, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/medicamentos")
    @Operation(summary = "Devolver medicamentos",
            description = "Este endpoint devuelve la lista de medicamentos según los parámetros de búsqueda.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de medicamentos"),
            @ApiResponse(responseCode = "204", description = "No se encontraron medicamentos según los parámetros de búsqueda")
    })
    public ResponseEntity<List<Medicamento>> devolverMedicamentos(@ModelAttribute MedicamentoBusqDTO medicamentoBusqDTO) {
        List<Medicamento> medicamentos = medicoServicios.devolverMedicamentos(
                medicamentoBusqDTO.getNombreComercial(),
                medicamentoBusqDTO.getPrincipioActivo(),
                medicamentoBusqDTO.getFabricante(),
                medicamentoBusqDTO.getFamilia());
        System.out.println(medicamentoBusqDTO);
        return medicamentos.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(medicamentos, HttpStatus.OK);
    }

    @PostMapping(path = "/nueva-prescripcion")
    @Operation(summary = "Crear nueva prescripción",
            description = "Este endpoint crea una nueva prescripción médica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prescripción creada exitosamente"),
            @ApiResponse(responseCode = "406", description = "No implementado")
    })
    public ResponseEntity<Prescripcion> crearPrescripcion(@RequestBody CrearPrescripcionDTO crearPrescripcionDTO) {
        Prescripcion prescripcionCreada = medicoServicios.crearPrescripcionMedico(
                crearPrescripcionDTO.getMedicamento(),
                crearPrescripcionDTO.getPrescripcionDTO().getNumColegiado(),
                crearPrescripcionDTO.getPrescripcionDTO().getNumTarjetaSanitaria(),
                crearPrescripcionDTO.getPrescripcionDTO().getDosis(),
                crearPrescripcionDTO.getPrescripcionDTO().getIndicaciones(),
                crearPrescripcionDTO.getPrescripcionDTO().getFechFinPres());
        return prescripcionCreada != null
                ? new ResponseEntity<>(prescripcionCreada, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Ver perfil del médico",
            description = "Este endpoint recupera el perfil de un médico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa del perfil del médico"),
            @ApiResponse(responseCode = "204", description = "Error al recuperar el perfil del médico")
    })
    public ResponseEntity<Medico> medicoPerfil(@PathVariable Long id) {
        Medico medicoPerfil = medicoServicios.viewMedico(id);
        return medicoPerfil != null
                ? new ResponseEntity<>(medicoPerfil, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Editar perfil del médico",
            description = "Este endpoint permite editar el perfil de un médico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil de médico editado exitosamente"),
            @ApiResponse(responseCode = "204", description = "Error al editar el perfil del médico")
    })
    public ResponseEntity<Medico> medicoEdit(@PathVariable Long id, @RequestBody Medico medico) {
        Medico editMedico = medicoServicios.editMedico(id, medico);
        return editMedico != null
                ? new ResponseEntity<>(editMedico, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/prescripciones/{numTarjetaSanitaria}")
    @Operation(summary = "Ver prescripciones del paciente",
            description = "Este endpoint permite ver las prescripciones de un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil de médico editado exitosamente"),
            @ApiResponse(responseCode = "204", description = "Error al editar el perfil del médico")
    })
    public ResponseEntity<List<Prescripcion>> devolverPacientesPrescripcion(@PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria){
        List<Prescripcion> listaPresPac = pacienteServicios.devolverPrescripcionPacientes(numTarjetaSanitaria);
        return listaPresPac.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(listaPresPac,HttpStatus.OK);
    }
}
