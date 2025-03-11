package models;

import java.sql.Timestamp;

public class Reservation {

    private Long idReservation;
    private Timestamp dateReservation;
    private Double prix;
    private Long idStatut;
    private Long idClasse;
    private Long idVol;
    private Long idUtilisateur;

    // Constructeur
    public Reservation(Long idReservation, Timestamp dateReservation, Double prix, Long idStatut, Long idClasse, Long idVol, Long idUtilisateur) {
        this.idReservation = idReservation;
        this.dateReservation = dateReservation;
        this.prix = prix;
        this.idStatut = idStatut;
        this.idClasse = idClasse;
        this.idVol = idVol;
        this.idUtilisateur = idUtilisateur;
    }

    // Getters et setters
    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Timestamp getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Timestamp dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public Long getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public Long getIdVol() {
        return idVol;
    }

    public void setIdVol(Long idVol) {
        this.idVol = idVol;
    }

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
