package models;

public class ConfVol {

    private Long idVol;
    private Long idClasse;
    private Long idCategorieAge;
    private Double montant;
    private Integer capacite;

    private String numeroVol;
    private String nomClasse;
    private String categorieAge;

    public ConfVol(String numeroVol, String nomClasse, String categorieAge, double montant, int capacite) {
        this.numeroVol = numeroVol;
        this.nomClasse = nomClasse;
        this.categorieAge = categorieAge;
        this.montant = montant;
        this.capacite = capacite;
    }

    public ConfVol(Long idVol, Long idClasse, Long idCategorieAge, Double montant, Integer capacite) {
        this.idVol = idVol;
        this.idClasse = idClasse;
        this.idCategorieAge = idCategorieAge;
        this.montant = montant;
        this.capacite = capacite;
    }

    // Constructeur
    public ConfVol(Long idVol, Long idClasse, Double montant, Integer capacite) {
        this.idVol = idVol;
        this.idClasse = idClasse;
        this.montant = montant;
        this.capacite = capacite;
    }

    public Long getIdCategorieAge() {
        return idCategorieAge;
    }

    public void setIdCategorieAge(Long idCategorieAge) {
        this.idCategorieAge = idCategorieAge;
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

    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public String getCategorieAge() {
        return categorieAge;
    }

    public void setCategorieAge(String categorieAge) {
        this.categorieAge = categorieAge;
    }
}
