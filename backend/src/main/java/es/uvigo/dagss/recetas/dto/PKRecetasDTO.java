package es.uvigo.dagss.recetas.dto;

public class PKRecetasDTO {
    private Long id_receta;
    private Long id_prescripcion;

    public PKRecetasDTO() {
    }

    public PKRecetasDTO(Long id_receta, Long id_prescripcion) {
        this.id_receta = id_receta;
        this.id_prescripcion = id_prescripcion;
    }

    public Long getId_receta() {
        return id_receta;
    }

    public void setId_receta(Long id_receta) {
        this.id_receta = id_receta;
    }

    public Long getId_prescripcion() {
        return id_prescripcion;
    }

    public void setId_prescripcion(Long id_prescripcion) {
        this.id_prescripcion = id_prescripcion;
    }
}
