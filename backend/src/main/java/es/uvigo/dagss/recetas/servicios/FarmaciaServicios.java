package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;

import java.util.List;

public interface FarmaciaServicios {
    List<Receta> busquedaRecetaPaciente(String numTarjetaSanitaria);
    Boolean servirReceta(Receta receta,String numTarjetaSanitaria);
    Farmacia editFarmacia(Farmacia editFarmacia);
    Farmacia viewFarmacia(String login);
}
