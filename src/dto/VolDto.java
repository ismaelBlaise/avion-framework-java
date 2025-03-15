package dto;

import annotation.FieldAnnotation;

public class VolDto {

    @FieldAnnotation(name = "numero")
    private String numero;
    @FieldAnnotation(name = "dateVol")
    private String dateVol;
    @FieldAnnotation(name = "heureDepart")
    private String heureDepart;
    @FieldAnnotation(name = "heureArrive")
    private String heureArrive;
    @FieldAnnotation(name = "heureReservation")
    private String heureReservation;
    @FieldAnnotation(name = "heureAnnulation")
    private String heureAnnulation;
    @FieldAnnotation(name = "idStatut")
    private String idStatut;
    @FieldAnnotation(name = "idVilleDepart")
    private String idVilleDepart;
    @FieldAnnotation(name = "idVilleArrive")
    private String idVilleArrive;
    @FieldAnnotation(name = "idAvion")
    private String idAvion;

    
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getDateVol() {
        return dateVol;
    }
    public void setDateVol(String dateVol) {
        this.dateVol = dateVol;
    }
    public String getHeureDepart() {
        return heureDepart;
    }
    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }
    public String getHeureArrive() {
        return heureArrive;
    }
    public void setHeureArrive(String heureArrive) {
        this.heureArrive = heureArrive;
    }
    public String getHeureReservation() {
        return heureReservation;
    }
    public void setHeureReservation(String heureReservation) {
        this.heureReservation = heureReservation;
    }
    public String getHeureAnnulation() {
        return heureAnnulation;
    }
    public void setHeureAnnulation(String heureAnnulation) {
        this.heureAnnulation = heureAnnulation;
    }
    public String getIdStatut() {
        return idStatut;
    }
    public void setIdStatut(String idStatut) {
        this.idStatut = idStatut;
    }
    public String getIdVilleDepart() {
        return idVilleDepart;
    }
    public void setIdVilleDepart(String idVilleDepart) {
        this.idVilleDepart = idVilleDepart;
    }
    public String getIdVilleArrive() {
        return idVilleArrive;
    }
    public void setIdVilleArrive(String idVilleArrive) {
        this.idVilleArrive = idVilleArrive;
    }
    public String getIdAvion() {
        return idAvion;
    }
    public void setIdAvion(String idAvion) {
        this.idAvion = idAvion;
    }

    // Getters and Setters

    
}
