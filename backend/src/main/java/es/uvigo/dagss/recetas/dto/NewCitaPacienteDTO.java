package es.uvigo.dagss.recetas.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Time;

public class NewCitaPacienteDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    @JsonFormat(pattern = "HH:mm:ss")
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
