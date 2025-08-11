package models;

public class Classe {

    private Long idClasse;
    private String classe;

    // Constructeur
    public Classe(Long idClasse, String classe) {
        this.idClasse = idClasse;
        this.classe = classe;
    }

    public Classe() {
        //TODO Auto-generated constructor stub
    }

    // Getters et setters
    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
