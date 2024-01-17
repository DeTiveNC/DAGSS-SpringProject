package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.TipoEstado;
import es.uvigo.dagss.recetas.repositorios.FarmaciaRepositorio;
import es.uvigo.dagss.recetas.repositorios.RecetaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Boolean servirReceta(String numTarjetaSanitaria, Receta receta) {
        List<Receta> recetaList = recetaRepositorio.findRecetasByEstado(numTarjetaSanitaria);
        TipoEstado tipoEstadoServida = TipoEstado.SERVIDA;
        LocalDate localDate = LocalDate.now();
        if (recetaList.contains(receta) && localDate.isBefore(receta.getFechFinVal().toLocalDate())){
            receta.setEstado(tipoEstadoServida);
            recetaRepositorio.save(receta);
            return true;
        }
        return false;
    }

    @Override
    public Farmacia editFarmacia(Long id, Farmacia editFarmacia) {
        Optional<Farmacia> farmaciaBusq = farmaciaRepositorio.findById(id);
        if (farmaciaBusq.isPresent() && farmaciaBusq.get().equals(editFarmacia)){
            farmaciaRepositorio.save(editFarmacia);
        }
        return null;
    }

    @Override
    public Farmacia viewFarmacia(Long id) {
        return farmaciaRepositorio.findById(id).get();
    }
}
