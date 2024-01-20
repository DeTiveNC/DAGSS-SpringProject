package es.uvigo.dagss.recetas.dto;

public class PMSearchDTO {
    private String numTarjetaSanitaria;
    private String numColegiado;

    public PMSearchDTO() {
    }

    public PMSearchDTO(String numTarjetaSanitaria, String numColegiado) {
        this.numTarjetaSanitaria = numTarjetaSanitaria;
        this.numColegiado = numColegiado;
    }

    public String getNumTarjetaSanitaria() {
        return numTarjetaSanitaria;
    }

    public void setNumTarjetaSanitaria(String numTarjetaSanitaria) {
        this.numTarjetaSanitaria = numTarjetaSanitaria;
    }

    public String getNumColegiado() {
        return numColegiado;
    }

    public void setNumColegiado(String numColegiado) {
        this.numColegiado = numColegiado;
    }
}
