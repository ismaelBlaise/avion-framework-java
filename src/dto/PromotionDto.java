package dto;

import annotation.FieldAnnotation;

public class PromotionDto {
    @FieldAnnotation(name = "idVol")
    String idVol;
    @FieldAnnotation(name = "idClasse")
    String idClasse;
    @FieldAnnotation(name = "pourcentage")
    String pourcentage;
    @FieldAnnotation(name = "nbSieze")
    String nbSieze;
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
    public String getPourcentage() {
        return pourcentage;
    }
    public void setPourcentage(String pourcentage) {
        this.pourcentage = pourcentage;
    }
    public String getNbSieze() {
        return nbSieze;
    }
    public void setNbSieze(String nbSieze) {
        this.nbSieze = nbSieze;
    }

}
