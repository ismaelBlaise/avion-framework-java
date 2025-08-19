package models.alea;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class ReservationPrix {

    private int idReservation;
    private int idVol;
    private int idClasse;
    private int idStatut;
    private int capacite;
    private LocalDateTime dateReservation;
    private int idReservationPrix;
    private BigDecimal prixConfigure;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private BigDecimal prixFacture;
    private int nbSieges;
    public ReservationPrix() {
    }
    public ReservationPrix(int idReservation, int idVol, int idClasse, int idStatut, LocalDateTime dateReservation,
            int idReservationPrix, BigDecimal prixConfigure, LocalDate dateDebut, LocalDate dateFin,
            BigDecimal prixFacture, int nbSieges) {
        this.idReservation = idReservation;
        this.idVol = idVol;
        this.idClasse = idClasse;
        this.idStatut = idStatut;
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
    public LocalDateTime getDateReservation() {
        return dateReservation;
    }
    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }
    public int getIdReservationPrix() {
        return idReservationPrix;
    }
    public void setIdReservationPrix(int idReservationPrix) {
        this.idReservationPrix = idReservationPrix;
    }
    public BigDecimal getPrixConfigure() {
        return prixConfigure;
    }
    public void setPrixConfigure(BigDecimal prixConfigure) {
        this.prixConfigure = prixConfigure;
    }
    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
    public LocalDate getDateFin() {
        return dateFin;
    }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }
    public BigDecimal getPrixFacture() {
        return prixFacture;
    }
    public void setPrixFacture(BigDecimal prixFacture) {
        this.prixFacture = prixFacture;
    }
    public int getNbSieges() {
        return nbSieges;
    }
    public void setNbSieges(int nbSieges) {
        this.nbSieges = nbSieges;
    }
    public int getCapacite() {
        return capacite;
    }
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

       
}
