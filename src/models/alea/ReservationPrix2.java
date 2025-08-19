package models.alea;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ReservationPrix2 {

    private int idReservation;
    private int idVol;
    private int idClasse;
    private int idStatut;
    private int capacite;
    private String dateReservation;
    private int idReservationPrix;
    private double prixConfigure;
    private String dateDebut;
    private String dateFin;
    private double prixFacture;
    private int nbSieges;
    public ReservationPrix2() {
    }
    public ReservationPrix2(int idReservation, int idVol, int idClasse, int idStatut, int capacite,
            String dateReservation, int idReservationPrix, double prixConfigure, String dateDebut, String dateFin,
            double prixFacture, int nbSieges) {
        this.idReservation = idReservation;
        this.idVol = idVol;
        this.idClasse = idClasse;
        this.idStatut = idStatut;
        this.capacite = capacite;
        this.dateReservation = dateReservation;
        this.idReservationPrix = idReservationPrix;
        this.prixConfigure = prixConfigure;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixFacture = prixFacture;
        this.nbSieges = nbSieges;
    }
    public int getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
    public int getIdVol() {
        return idVol;
    }
    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }
    public int getIdClasse() {
        return idClasse;
    }
    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }
    public int getIdStatut() {
        return idStatut;
    }
    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }
    public int getCapacite() {
        return capacite;
    }
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
    public String getDateReservation() {
        return dateReservation;
    }
    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }
    public int getIdReservationPrix() {
        return idReservationPrix;
    }
    public void setIdReservationPrix(int idReservationPrix) {
        this.idReservationPrix = idReservationPrix;
    }
    public double getPrixConfigure() {
        return prixConfigure;
    }
    public void setPrixConfigure(double prixConfigure) {
        this.prixConfigure = prixConfigure;
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
    public double getPrixFacture() {
        return prixFacture;
    }
    public void setPrixFacture(double prixFacture) {
        this.prixFacture = prixFacture;
    }
    public int getNbSieges() {
        return nbSieges;
    }
    public void setNbSieges(int nbSieges) {
        this.nbSieges = nbSieges;
    }
    
       
}
