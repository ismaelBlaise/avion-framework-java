package dto;

import annotation.FieldAnnotation;

public class RechercheDto {
    @FieldAnnotation(name = "numero")
    String numero;
    @FieldAnnotation(name = "dateDebut")
    String dateDebut;
    @FieldAnnotation(name = "dateFin")
    String dateFin;
    @FieldAnnotation(name = "heureDebut")
    String heureDebut;
    @FieldAnnotation(name = "heureFin")
    String heureFin;
    @FieldAnnotation(name = "idVilleDepart")
    String villeDepart;
    @FieldAnnotation(name = "idVilleArrive")
    String villeArrive;
    @FieldAnnotation(name = "idStatut")
    String idStatut;
    @FieldAnnotation(name = "idAvion")
    String idAvion;
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }
    public String getDateFin() {
        return dateFin;
    }
    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }
    public String getHeureDebut() {
        return heureDebut;
    }
    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }
    public String getHeureFin() {
        return heureFin;
    }
    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }
    public String getVilleDepart() {
        return villeDepart;
    }
    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }
    public String getVilleArrive() {
        return villeArrive;
    }
    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }
    public String getIdStatut() {
        return idStatut;
    }
    public void setIdStatut(String idStatut) {
        this.idStatut = idStatut;
    }
    public String getIdAvion() {
        return idAvion;
    }
    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }
}
