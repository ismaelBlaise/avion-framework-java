package dto;

import annotation.FieldAnnotation;

public class ConfVolDto {
    
    @FieldAnnotation(name = "idVol")
    String idVol;
    @FieldAnnotation(name = "idClasse")
    String idClasse;
    @FieldAnnotation(name = "idCategorieAge")
    String idCategorieAge;
    @FieldAnnotation(name = "montant")
    String montant;
    @FieldAnnotation(name = "capacite")
    String capacite;

    public ConfVolDto() {
    }
    
    public String getIdVol() {
        return idVol;
    }
    public void setIdVol(String idVol) {
        this.idVol = idVol;
    }
    public String getIdClasse() {
        return idClasse;
    }
    public void setIdClasse(String idClasse) {
        this.idClasse = idClasse;
    }
    public String getIdCategorieAge() {
        return idCategorieAge;
    }
    public void setIdCategorieAge(String idCategorieAge) {
        this.idCategorieAge = idCategorieAge;
    }
    public String getMontant() {
        return montant;
    }
    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }    
}
