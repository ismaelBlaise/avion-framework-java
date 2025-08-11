package models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReservationDetail {
    private int idReservation;
    private int idCategorieAge;
    private int idClasse;
    private BigDecimal prix;           // NUMERIC → BigDecimal
    private byte[] passeport;          // BYTEA
    private String nomFichier;         // nom_fichier
    private LocalDateTime dateDepot;   // date_depot
    private String categorieAge;
    private String classe;

    public ReservationDetail(int idReservation, int idCategorieAge, int idClasse, BigDecimal prix,
                              byte[] passeport, String nomFichier, LocalDateTime dateDepot,
                              String categorieAge, String classe) {
        this.idReservation = idReservation;
        this.idCategorieAge = idCategorieAge;
        this.idClasse = idClasse;
        this.prix = prix;
        this.passeport = passeport;
        this.nomFichier = nomFichier;
        this.dateDepot = dateDepot;
        this.categorieAge = categorieAge;
        this.classe = classe;
    }

    public ReservationDetail() {}

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getIdCategorieAge() {
        return idCategorieAge;
    }

    public void setIdCategorieAge(int idCategorieAge) {
        this.idCategorieAge = idCategorieAge;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public byte[] getPasseport() {
        return passeport;
    }

    public void setPasseport(byte[] passeport) {
        this.passeport = passeport;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }

    public LocalDateTime getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(LocalDateTime dateDepot) {
        this.dateDepot = dateDepot;
    }

    public String getCategorieAge() {
        return categorieAge;
    }

    public void setCategorieAge(String categorieAge) {
        this.categorieAge = categorieAge;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
