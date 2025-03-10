package models;

public class ConfVol {

    private Long idVol;
    private Long idClasse;
    private Double montant;
    private Integer capacite;

    // Constructeur
    public ConfVol(Long idVol, Long idClasse, Double montant, Integer capacite) {
        this.idVol = idVol;
        this.idClasse = idClasse;
        this.montant = montant;
        this.capacite = capacite;
    }

    // Getters et setters
    public Long getIdVol() {
        return idVol;
    }

    public void setIdVol(Long idVol) {
        this.idVol = idVol;
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }
}
