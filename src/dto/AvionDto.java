package dto;

import annotation.FieldAnnotation;

public class AvionDto {

    @FieldAnnotation(name = "capacite")
    String capacite;
    @FieldAnnotation(name = "modele")
    String modele;
    public String getCapacite() {
        return capacite;
    }
    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }
    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }

}
