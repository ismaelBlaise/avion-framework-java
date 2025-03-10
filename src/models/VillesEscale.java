package models;

public class VillesEscale {

    private Long idVol;
    private Long idVille;

    // Constructeur
    public VillesEscale(Long idVol, Long idVille) {
        this.idVol = idVol;
        this.idVille = idVille;
    }

    // Getters et setters
    public Long getIdVol() {
        return idVol;
    }

    public void setIdVol(Long idVol) {
        this.idVol = idVol;
    }

    public Long getIdVille() {
        return idVille;
    }

    public void setIdVille(Long idVille) {
        this.idVille = idVille;
    }
}
