package models;

import java.sql.Timestamp;

public class Reservation {
    private int idReservation;
    private Timestamp dateReservation;
    private int idStatut;
    private int idClasse;
    private int idVol;

    // Constructeur par défaut
    public Reservation() {
        this.dateReservation = new Timestamp(System.currentTimeMillis());
    }

    // Constructeur avec paramètres
    public Reservation(int idReservation, Timestamp dateReservation, int idStatut, int idClasse, int idVol) {
        this.idReservation = idReservation;
        this.dateReservation = dateReservation;
        this.idStatut = idStatut;
        this.idClasse = idClasse;
        this.idVol = idVol;
    }

    // Getters et Setters
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Timestamp getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Timestamp dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(int idStatut) {
        this.idStatut = idStatut;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    // Méthode toString() pour affichage
    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", dateReservation=" + dateReservation +
                ", idStatut=" + idStatut +
                ", idClasse=" + idClasse +
                ", idVol=" + idVol +
                '}';
    }
}
