package models;

public class Vol {

    private Long idVol;
    private String numero;
    private String dateVol;
    private String heureDepart;
    private String heureArrive;
    private String heureReservation;
    private String heureAnnulation;
    private Long idStatut;
    private Long idVilleDepart;
    private Long idVilleArrive;
    private Long idAvion;

    // Constructeur
    public Vol(Long idVol, String numero, String dateVol, String heureDepart, String heureArrive, String heureReservation, String heureAnnulation, Long idStatut, Long idVilleDepart, Long idVilleArrive, Long idAvion) {
        this.idVol = idVol;
        this.numero = numero;
        this.dateVol = dateVol;
        this.heureDepart = heureDepart;
        this.heureArrive = heureArrive;
        this.heureReservation = heureReservation;
        this.heureAnnulation = heureAnnulation;
        this.idStatut = idStatut;
        this.idVilleDepart = idVilleDepart;
        this.idVilleArrive = idVilleArrive;
        this.idAvion = idAvion;
    }

    // Getters et setters
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
