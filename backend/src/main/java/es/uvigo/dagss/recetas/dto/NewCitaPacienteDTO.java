package es.uvigo.dagss.recetas.dto;

import java.sql.Date;
import java.sql.Time;

public class NewCitaPacienteDTO {
    private Date fecha;
    private Time hora;

    public NewCitaPacienteDTO( Date fecha, Time hora) {
        this.fecha = fecha;
        this.hora = hora;
    }

    public NewCitaPacienteDTO() {
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
