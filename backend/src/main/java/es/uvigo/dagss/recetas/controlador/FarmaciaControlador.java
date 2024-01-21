package es.uvigo.dagss.recetas.controlador;

import es.uvigo.dagss.recetas.dto.PKRecetasDTO;
import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.servicios.FarmaciaServicios;
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
@RequestMapping(path = "/home/farmacias")
@Tag(name = "Farmacia Endpoint", description = "Farmacia Controlador")
public class FarmaciaControlador {

    @Autowired
    private FarmaciaServicios farmaciaServicios;

    @GetMapping(path = "/recetas/{numTarjetaSanitaria}")
    @Operation(summary = "Recuperar recetas para un paciente específico",
            description = "Este endpoint recupera una lista de recetas para el paciente especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa de recetas"),
            @ApiResponse(responseCode = "204", description = "No se encontraron recetas para el paciente especificado")
    })
    public ResponseEntity<List<Receta>> devolverRecetasPaciente(
            @PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria) {
        List<Receta> recetasPaciente = farmaciaServicios.busquedaRecetaPaciente(numTarjetaSanitaria);
        return recetasPaciente.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(recetasPaciente, HttpStatus.OK);
    }

    @PatchMapping(path = "/{id}/{numTarjetaSanitaria}")
    @Operation(summary = "Servir una receta a un paciente",
            description = "Este endpoint sirve una receta a un paciente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Receta servida exitosamente"),
            @ApiResponse(responseCode = "204", description = "Error al servir la receta")
    })
    public ResponseEntity<Receta> servirRecetaPaciente(
            @PathVariable("id") Long id, @PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria,
            @RequestBody PKRecetasDTO pkRecetasDTO) {
        Receta recetaAcrear = farmaciaServicios.servirReceta(id, numTarjetaSanitaria, pkRecetasDTO.getId_receta(), pkRecetasDTO.getId_prescripcion());
        return recetaAcrear != null
                ? new ResponseEntity<>(recetaAcrear, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Editar perfil de la farmacia",
            description = "Este endpoint permite editar el perfil de una farmacia.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfil de farmacia editado exitosamente"),
            @ApiResponse(responseCode = "204", description = "Error al editar el perfil de la farmacia")
    })
    public ResponseEntity<Farmacia> editFarmacia(@PathVariable("id") Long id, @RequestBody Farmacia farmacia) {
        Farmacia editFarmacia = farmaciaServicios.editFarmacia(id, farmacia);
        return editFarmacia != null
                ? new ResponseEntity<>(editFarmacia, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Ver perfil de la farmacia",
            description = "Este endpoint recupera el perfil de una farmacia.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recuperación exitosa del perfil de la farmacia"),
            @ApiResponse(responseCode = "400", description = "Error al recuperar el perfil de la farmacia")
    })
    public ResponseEntity<Farmacia> viewFarmacia(@PathVariable("id") Long id) {
        Farmacia farmaciaView = farmaciaServicios.viewFarmacia(id);
        return farmaciaView != null
                ? new ResponseEntity<>(farmaciaView, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
