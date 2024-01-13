package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.TipoEstado;
import es.uvigo.dagss.recetas.repositorios.FarmaciaRepositorio;
import es.uvigo.dagss.recetas.repositorios.RecetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class FarmaciaServiciosImpl implements FarmaciaServicios{
    @Autowired
    private FarmaciaRepositorio farmaciaRepositorio;
    @Autowired
    private RecetaRepositorio recetaRepositorio;
    @Override
    public List<Receta> busquedaRecetaPaciente(String numTarjetaSanitaria) {
        return recetaRepositorio.findRecetasByEstado(numTarjetaSanitaria);
    }

    @Override
    public Boolean servirReceta(Receta receta, String numTarjetaSanitaria) {
        List<Receta> recetaList = recetaRepositorio.findRecetasByEstado(numTarjetaSanitaria);
        TipoEstado tipoEstadoServida = TipoEstado.SERVIDA;
        LocalDate localDate = null;
        if (recetaList.contains(receta) && (!Objects.equals(receta.getFechFinVal().toLocalDate(), LocalDate.now()))){
            receta.setEstado(tipoEstadoServida);
            recetaRepositorio.save(receta);
            return true;
        }
        return false;
    }

    @Override
    public Farmacia editFarmacia(Farmacia editFarmacia) {
        return farmaciaRepositorio.save(editFarmacia);
    }

    @Override
    public Farmacia viewFarmacia(String login) {
        return farmaciaRepositorio.findFarmaciaByLogin(login).get();
    }
}
