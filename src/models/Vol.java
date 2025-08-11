package models;


public class Vol {

    private Long idVol;
    private String numero;
    private String depart;            // datetime en Java 8+
    private String arrivee;
    private String finReservation;
    private String finAnnulation;
    private Long idStatut;
    private Long idVilleDepart;
    private Long idVilleArrive;
    private Long idAvion;
    
    public Vol() {
    }
    public Vol(Long idVol, String numero, String depart, String arrivee, String finReservation, String finAnnulation,
            Long idStatut, Long idVilleDepart, Long idVilleArrive, Long idAvion) {
        this.idVol = idVol;
        this.numero = numero;
        this.depart = depart;
        this.arrivee = arrivee;
        this.finReservation = finReservation;
        this.finAnnulation = finAnnulation;
        this.idStatut = idStatut;
        this.idVilleDepart = idVilleDepart;
        this.idVilleArrive = idVilleArrive;
        this.idAvion = idAvion;
    }
    public Long getIdVol() {
        return idVol;
    }
    public void setIdVol(Long idVol) {
        this.idVol = idVol;
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
    public String getArrivee() {
        return arrivee;
    }
    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
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
    public Long getIdStatut() {
        return idStatut;
    }
    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }
    public Long getIdVilleDepart() {
        return idVilleDepart;
    }
    public void setIdVilleDepart(Long idVilleDepart) {
        this.idVilleDepart = idVilleDepart;
    }
    public Long getIdVilleArrive() {
        return idVilleArrive;
    }
    public void setIdVilleArrive(Long idVilleArrive) {
        this.idVilleArrive = idVilleArrive;
    }
    public Long getIdAvion() {
        return idAvion;
    }
    public void setIdAvion(Long idAvion) {
        this.idAvion = idAvion;
    }

   
}
