package models;

import java.sql.Timestamp;

public class Reservation {
    private int idReservation;
    private Timestamp dateReservation;
    private int idUtilisateur;
    private int idStatut;
    private int idClasse;
    private int idVol;
    private String statutNom;
    private String utilisateurNom;
    private String volNom;
    private String classeNom;
    public Reservation(int idReservation, Timestamp dateReservation, int idUtilisateur, int idStatut, int idClasse,
            int idVol) {
        this.idReservation = idReservation;
        this.dateReservation = dateReservation;
        this.idUtilisateur = idUtilisateur;
        this.idStatut = idStatut;
        this.idClasse = idClasse;
        this.idVol = idVol;
    }
    public Reservation() {
       
    }
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
    public int getIdUtilisateur() {
        return idUtilisateur;
    }
    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
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
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idReservation;
        result = prime * result + ((dateReservation == null) ? 0 : dateReservation.hashCode());
        result = prime * result + idUtilisateur;
        result = prime * result + idStatut;
        result = prime * result + idClasse;
        result = prime * result + idVol;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reservation other = (Reservation) obj;
        if (idReservation != other.idReservation)
            return false;
        if (dateReservation == null) {
            if (other.dateReservation != null)
                return false;
        } else if (!dateReservation.equals(other.dateReservation))
            return false;
        if (idUtilisateur != other.idUtilisateur)
            return false;
        if (idStatut != other.idStatut)
            return false;
        if (idClasse != other.idClasse)
            return false;
        if (idVol != other.idVol)
            return false;
        return true;
    }
    public String getStatutNom() {
        return statutNom;
    }
    public void setStatutNom(String statutNom) {
        this.statutNom = statutNom;
    }
    public String getUtilisateurNom() {
        return utilisateurNom;
    }
    public void setUtilisateurNom(String utilisateurNom) {
        this.utilisateurNom = utilisateurNom;
    }
    public String getVolNom() {
        return volNom;
    }
    public void setVolNom(String volNom) {
        this.volNom = volNom;
    }
    public String getClasseNom() {
        return classeNom;
    }
    public void setClasseNom(String classeNom) {
        this.classeNom = classeNom;
    }


}
