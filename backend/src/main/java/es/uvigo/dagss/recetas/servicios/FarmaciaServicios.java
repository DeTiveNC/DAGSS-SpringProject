package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;

import java.util.List;

public interface FarmaciaServicios {
    List<Receta> busquedaRecetaPaciente(String numTarjetaSanitaria);
    Boolean servirReceta(String numTarjetaSanitaria, Receta receta);
    Farmacia editFarmacia(Long id,Farmacia editFarmacia);
    Farmacia viewFarmacia(Long id);
}