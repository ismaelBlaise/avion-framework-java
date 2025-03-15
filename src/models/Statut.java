package models;

public class Statut {

    private Long idStatut;
    private String statut;

    public Statut() {
    }

    // Constructeur
    public Statut(Long idStatut, String statut) {
        this.idStatut = idStatut;
        this.statut = statut;
    }

    // Getters et setters
    public Long getIdStatut() {
        return idStatut;
    }

    public void setIdStatut(Long idStatut) {
        this.idStatut = idStatut;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
