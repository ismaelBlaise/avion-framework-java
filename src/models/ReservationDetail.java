package models;

public class ReservationDetail {
    private int idReservation;
    private int idCategorieAge;
    private int idClasse;
    private double prix;
    private int nb;
    private String categorieAge;
    private String classe;
    public ReservationDetail(int idReservation, int idCategorieAge, int idClasse, double prix, int nb,
            String categorieAge, String classe) {
        this.idReservation = idReservation;
        this.idCategorieAge = idCategorieAge;
        this.idClasse = idClasse;
        this.prix = prix;
        this.nb = nb;
        this.categorieAge = categorieAge;
        this.classe = classe;
    }
    public ReservationDetail() {
    }
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
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getNb() {
        return nb;
    }
    public void setNb(int nb) {
        this.nb = nb;
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
