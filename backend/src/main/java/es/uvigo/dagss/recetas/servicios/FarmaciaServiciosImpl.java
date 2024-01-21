package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.TipoEstado;
import es.uvigo.dagss.recetas.repositorios.FarmaciaRepositorio;
import es.uvigo.dagss.recetas.repositorios.RecetaRepositorio;
import jakarta.persistence.EntityManager;
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
    public Receta servirReceta(Long id_farmacia,String numTarjetaSanitaria, Long id_receta, Long id_prescripcion) {
        TipoEstado tipoEstadoServida = TipoEstado.SERVIDA;
        LocalDate localDate = LocalDate.now();
        Optional<Receta> recetaExtraida = recetaRepositorio.findRecetaByID(id_receta, id_prescripcion);
        if(!recetaExtraida.isPresent()){
            return null;
        }
        Receta receta= recetaExtraida.get();
        Optional<Farmacia> farmaciaServidora = farmaciaRepositorio.findById(id_farmacia );
        if (localDate.isBefore(receta.getFechFinVal().toLocalDate()) && farmaciaServidora.isPresent() && receta.getFarmacia()==null){
            receta.setEstado(tipoEstadoServida);
            receta.setFarmacia(farmaciaServidora.get());
            return recetaRepositorio.save(receta);
        }
        return null;
    }
    @Override
    public Farmacia editFarmacia(Long id, Farmacia editFarmacia) {
        Optional<Farmacia> farmaciaBusq = farmaciaRepositorio.findById(id);
        if (farmaciaBusq.isPresent() && farmaciaBusq.get().getId().equals(editFarmacia.getId())){
            farmaciaRepositorio.save(editFarmacia);
        }
        return null;
    }
    @Override
    public Farmacia viewFarmacia(Long id) {
        return farmaciaRepositorio.findById(id).get();
    }
}
