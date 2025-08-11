package models;

public class Ville {

    private Long idVille;
    private String ville;

    public Ville() {
    }

    // Constructeur
    public Ville(Long idVille, String ville) {
        this.idVille = idVille;
        this.ville = ville;
    }

    // Getters et setters
    public Long getIdVille() {
        return idVille;
    }

    public void setIdVille(Long idVille) {
        this.idVille = idVille;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
