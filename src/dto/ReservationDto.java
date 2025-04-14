package dto;

import annotation.FieldAnnotation;

public class ReservationDto {
    @FieldAnnotation(name = "idReservation")
    private String idReservation;

    @FieldAnnotation(name = "dateReservation")
    private String dateReservation;

    @FieldAnnotation(name = "idStatut")
    private String idStatut;

    // @FieldAnnotation(name = "idClasse")
    // private String idClasse;

    @FieldAnnotation(name = "idVol")
    private String idVol;

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(String idStatut) {
        this.idStatut = idStatut;
    }

    // public String getIdClasse() {
    //     return idClasse;
    // }

    // public void setIdClasse(String idClasse) {
    //     this.idClasse = idClasse;
    // }

    public String getIdVol() {
        return idVol;
    }

    public void setIdVol(String idVol) {
        this.idVol = idVol;
    }

    
}
