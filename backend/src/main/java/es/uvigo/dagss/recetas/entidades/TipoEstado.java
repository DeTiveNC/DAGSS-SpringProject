package es.uvigo.dagss.recetas.entidades;

public enum TipoEstado {
    PLANIFICADA ("PLANIFICADA"),
    ANULADA      ("ANULADA"),
    COMPLETADA        ("COMPLETADA"),
    AUSENTE        ("AUSENTE"),
    INACTIVA      ("INACTIVA"),
    INACTIVO      ("INACTIVO"),
    ACTIVO      ("ACTIVO"),
    SERVIDA     ("SERVIDA");

    private final String etiqueta;

    private TipoEstado(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getEtiqueta() {
        return etiqueta;
    }
}
