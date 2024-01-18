package es.uvigo.dagss.recetas.controlador;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.servicios.FarmaciaServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/home/farmacias")
public class FarmaciaControlador {
    @Autowired
    private FarmaciaServicios farmaciaServicios;
    @GetMapping(path = "/{numTarjetaSanitaria}")
    public ResponseEntity<List<Receta>> devolverRecetasPaciente(@PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria){
        List<Receta> recetasPaciente = farmaciaServicios.busquedaRecetaPaciente(numTarjetaSanitaria);
        if (!recetasPaciente.isEmpty()){
            return new ResponseEntity<>(recetasPaciente, HttpStatus.OK);
        }
        return new ResponseEntity<>(recetasPaciente, HttpStatus.NO_CONTENT);
    }
    @PutMapping(path = "/{id}/{numTarjetaSanitaria}")
    public ResponseEntity<Receta> servirRecetaPaciente(@PathVariable("id") Long id,@PathVariable("numTarjetaSanitaria") String numTarjetaSanitaria, @RequestBody Receta receta){
        Receta recetaAcrear = farmaciaServicios.servirReceta(id, numTarjetaSanitaria, receta);
        if (recetaAcrear != null){
            return new ResponseEntity<>(recetaAcrear, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recetaAcrear, HttpStatus.OK);
    }
    @PutMapping(path = "/{id}/perfil")
    public ResponseEntity<Farmacia> editFarmacia(@PathVariable("id") Long id,@RequestBody Farmacia farmacia){
        Farmacia editFarmacia = farmaciaServicios.editFarmacia(id, farmacia);
        if (editFarmacia != null){
            return new ResponseEntity<>(editFarmacia,HttpStatus.OK);
        }
        return new ResponseEntity<>(editFarmacia, HttpStatus.NO_CONTENT);
    }
    @GetMapping(path = "/{id}/perfil")
    public ResponseEntity<Farmacia> viewFarmacia(@PathVariable("id") Long id){
        Farmacia farmaciaView = farmaciaServicios.viewFarmacia(id);
        if (farmaciaView != null) {
            return new ResponseEntity<>(farmaciaView, HttpStatus.OK);
        }
        return new ResponseEntity<>(farmaciaView, HttpStatus.BAD_REQUEST);
    }
}
