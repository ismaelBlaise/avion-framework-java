package models;

public class Statut {

    private Long idStatut;
    private String statut;
    private String source;

    
    public Statut() {
    }
    public Statut(Long idStatut, String statut, String source) {
        this.idStatut = idStatut;
        this.statut = statut;
        this.source = source;
    }
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
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

   
}
