package es.uvigo.dagss.recetas.servicios;

import es.uvigo.dagss.recetas.entidades.Farmacia;
import es.uvigo.dagss.recetas.entidades.Receta;
import es.uvigo.dagss.recetas.entidades.TipoEstado;
import es.uvigo.dagss.recetas.daos.FarmaciaDAO;
import es.uvigo.dagss.recetas.daos.RecetaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FarmaciaServiciosImpl implements FarmaciaServicios{
    @Autowired
    private FarmaciaDAO farmaciaDAO;
    @Autowired
    private RecetaDAO recetaDAO;
    @Override
    public List<Receta> busquedaRecetaPaciente(String numTarjetaSanitaria) {
        return recetaDAO.findRecetasByEstado(numTarjetaSanitaria);
    }
    @Override
    public Receta servirReceta(Long id_farmacia,String numTarjetaSanitaria, Long id_receta, Long id_prescripcion) {
        TipoEstado tipoEstadoServida = TipoEstado.SERVIDA;
        LocalDate localDate = LocalDate.now();
        Optional<Receta> recetaExtraida = recetaDAO.findRecetaByID(id_receta, id_prescripcion, numTarjetaSanitaria);
        if(!recetaExtraida.isPresent()){
            return null;
        }
        Receta receta= recetaExtraida.get();
        Optional<Farmacia> farmaciaServidora = farmaciaDAO.findById(id_farmacia );
        if (localDate.isBefore(receta.getFechFinVal().toLocalDate()) && farmaciaServidora.isPresent() && receta.getFarmacia()==null){
            receta.setEstado(tipoEstadoServida);
            receta.setFarmacia(farmaciaServidora.get());
            return recetaDAO.save(receta);
        }
        return null;
    }
    @Override
    public Farmacia editFarmacia(Long id, Farmacia editFarmacia) {
        Optional<Farmacia> farmaciaBusq = farmaciaDAO.findById(id);
        if (farmaciaBusq.isPresent() && farmaciaBusq.get().getId().equals(editFarmacia.getId())){
            farmaciaDAO.save(editFarmacia);
        }
        return null;
    }
    @Override
    public Farmacia viewFarmacia(Long id) {
        return farmaciaDAO.findById(id).get();
    }
}
