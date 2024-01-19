package es.uvigo.dagss.recetas.dto;

import es.uvigo.dagss.recetas.entidades.Medicamento;

public class CrearPrescripcionDTO {
    private Medicamento medicamento;
    private PrescripcionDTO prescripcionDTO;

    public CrearPrescripcionDTO() {
    }

    public CrearPrescripcionDTO(Medicamento medicamento, PrescripcionDTO prescripcionDTO) {
        this.medicamento = medicamento;
        this.prescripcionDTO = prescripcionDTO;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public PrescripcionDTO getPrescripcionDTO() {
        return prescripcionDTO;
    }

    public void setPrescripcionDTO(PrescripcionDTO prescripcionDTO) {
        this.prescripcionDTO = prescripcionDTO;
    }
}
