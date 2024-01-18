package es.uvigo.dagss.recetas.controlador;

import es.uvigo.dagss.recetas.dto.NewCitaPacienteDTO;
import es.uvigo.dagss.recetas.entidades.Cita;
import es.uvigo.dagss.recetas.entidades.Paciente;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.servicios.PacienteServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/home/pacientes")
public class PacienteControlador {
    @Autowired
    private PacienteServicios pacienteServicios;
    @GetMapping(path = "/citas/{numTarjetaSanitaria}")
    public ResponseEntity<List<Cita>> devolverCitasPaciente(@PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria){
        List<Cita> citasPaciente = pacienteServicios.devolverCitasPaciente(numTarjetaSanitaria);
        if (!citasPaciente.isEmpty()){
            return new ResponseEntity<>(citasPaciente, HttpStatus.OK);
        }
        return new ResponseEntity<>(citasPaciente, HttpStatus.NO_CONTENT);
    }
    @PutMapping(path = "/citas/{numTarjetaSanitaria}")
    public ResponseEntity<Cita> anularCitaPaciente(@PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria, @RequestBody Cita cita){
        Cita citaAnulada = pacienteServicios.anularCitaPaciente(numTarjetaSanitaria, cita);
        if (citaAnulada != null){
            return new ResponseEntity<>(citaAnulada, HttpStatus.OK);
        }
        return new ResponseEntity<>(citaAnulada, HttpStatus.NO_CONTENT);
    }
    @PostMapping(path = "/citas/{id}")
    public ResponseEntity<Cita> crearCitaPaciente(@PathVariable("id") Long id, @RequestBody NewCitaPacienteDTO citaPaciente){
        Cita newCitaPaciente = pacienteServicios.crearCitaPaciente(id, citaPaciente.getNumColegiado(),citaPaciente.getFecha(),citaPaciente.getHora());
        if (newCitaPaciente != null){
            return new ResponseEntity<>(newCitaPaciente, HttpStatus.OK);
        }
        return new ResponseEntity<>(newCitaPaciente, HttpStatus.BAD_REQUEST);
    }
    @GetMapping(path = "/recetas/{numTarjetaSanitaria}")
    public ResponseEntity<List<Receta>> devolverRecetasPaciente(@PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria){
        List<Receta> recetasPaciente = pacienteServicios.obtenerRecetasPaciente(numTarjetaSanitaria);
        if (!recetasPaciente.isEmpty()){
            return new ResponseEntity<>(recetasPaciente, HttpStatus.OK);
        }
        return new ResponseEntity<>(recetasPaciente, HttpStatus.NO_CONTENT);
    }
    @GetMapping(path = "/{id}/perfil")
    public ResponseEntity<Paciente> perfilPaciente(@PathVariable("id") Long id){
        Paciente pacienteView = pacienteServicios.viewPaciente(id);
        if (pacienteView != null){
            return new ResponseEntity<>(pacienteView, HttpStatus.OK);
        }
        return new ResponseEntity<>(pacienteView, HttpStatus.BAD_REQUEST);
    }
    @PutMapping(path = "/{id}/perfil")
    public ResponseEntity<Paciente> perfilPaciente(@PathVariable("id") Long id, @RequestBody Paciente pacienteEditar){
        Paciente pacienteEditado = pacienteServicios.editPaciente(id, pacienteEditar);
        if (pacienteEditado != null){
            return new ResponseEntity<>(pacienteEditado, HttpStatus.OK);
        }
        return new ResponseEntity<>(pacienteEditado, HttpStatus.BAD_REQUEST);
    }
}
