package es.uvigo.dagss.recetas.dto;

import java.sql.Date;
import java.sql.Time;

public class MedicoCitaDTO {
    private Date fecha;
    private Time hora;

    public MedicoCitaDTO(String numColegiado, Date fecha, Time hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public MedicoCitaDTO() {
    }
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

}
