package dto;

import annotation.FieldAnnotation;

public class VolDto {

    @FieldAnnotation(name = "numero")
    private String numero;
    @FieldAnnotation(name = "depart")
    private String depart;
    @FieldAnnotation(name = "arrive")
    private String arrive;
    // @FieldAnnotation(name = "heureReservation")
    private String finReservation;
    // @FieldAnnotation(name = "finAnnulation")
    private String finAnnulation;
    @FieldAnnotation(name = "idStatut")
    private String idStatut;
    @FieldAnnotation(name = "idVilleDepart")
    private String idVilleDepart;
    @FieldAnnotation(name = "idVilleArrive")
    private String idVilleArrive;
    @FieldAnnotation(name = "idAvion")
    private String idAvion;


    
    public VolDto() {
    }
    public VolDto(String numero, String depart, String arrive, String finReservation, String finAnnulation,
            String idStatut, String idVilleDepart, String idVilleArrive, String idAvion) {
        this.numero = numero;
        this.depart = depart;
        this.arrive = arrive;
        this.finReservation = finReservation;
        this.finAnnulation = finAnnulation;
        this.idStatut = idStatut;
        this.idVilleDepart = idVilleDepart;
        this.idVilleArrive = idVilleArrive;
        this.idAvion = idAvion;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getDepart() {
        return depart;
    }
    public void setDepart(String depart) {
        this.depart = depart;
    }
    public String getArrive() {
        return arrive;
    }
    public void setArrive(String arrive) {
        this.arrive = arrive;
    }
    public String getFinReservation() {
        return finReservation;
    }
    public void setFinReservation(String finReservation) {
        this.finReservation = finReservation;
    }
    public String getFinAnnulation() {
        return finAnnulation;
    }
    public void setFinAnnulation(String finAnnulation) {
        this.finAnnulation = finAnnulation;
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

    
    

    
}
