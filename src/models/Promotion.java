package models;

public class Promotion {

    private Long idVol;
    private Long idClasse;
    private String nomClasse;
    private Double pourcentage;
    private Integer nbSiege;

    public Promotion(Long idClasse, String nomClasse, Double pourcentage, Integer nbSiege) {
        this.idClasse = idClasse;
        this.nomClasse = nomClasse;
        this.pourcentage = pourcentage;
        this.nbSiege = nbSiege;
    }

    // Constructeur
    public Promotion(Long idVol, Long idClasse, Double pourcentage, Integer nbSiege) {
        this.idVol = idVol;
        this.idClasse = idClasse;
        this.pourcentage = pourcentage;
        this.nbSiege = nbSiege;
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

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Integer getNbSiege() {
        return nbSiege;
    }

    public void setNbSiege(Integer nbSiege) {
        this.nbSiege = nbSiege;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }
}
