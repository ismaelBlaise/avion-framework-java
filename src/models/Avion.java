package models;


public class Avion{

    private Long idAvion;
    private Integer capacite;
    private String modele;

    public Avion() {
    }

    // Constructeur
    public Avion(Long idAvion, Integer capacite, String modele) {

        this.idAvion = idAvion;
        this.capacite = capacite;
        this.modele = modele;
    }

    // Getters et setters
    public Long getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Long idAvion) {
        this.idAvion = idAvion;
    }

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
        this.capacite = capacite;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }
}
