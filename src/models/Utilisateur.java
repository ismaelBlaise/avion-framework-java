package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Utilisateur {

    private Long idUtilisateur;
    private String nom;
    private String prenom;
    private String email;
    private String dateNaissance;
    private String contact;
    private String mdp;
    private Long idRole;

    // Constructeur
    public Utilisateur(Long idUtilisateur, String nom, String prenom, String email, String dateNaissance, String contact, String mdp, Long idRole) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.contact = contact;
        this.mdp = mdp;
        this.idRole = idRole;
    }

    public Utilisateur() {
        // Constructeur vide
    }

    // Getters et setters
    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public Utilisateur toUtilisateur(ResultSet resultSet) throws SQLException {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(resultSet.getLong("id_utilisateur"));
        utilisateur.setNom(resultSet.getString("nom"));
        utilisateur.setPrenom(resultSet.getString("prenom"));
        utilisateur.setEmail(resultSet.getString("email"));
        // utilisateur.setDateNaissance(resultSet.getString("date_naissance"));
        utilisateur.setContact(resultSet.getString("contact"));
        utilisateur.setMdp(resultSet.getString("mdp"));
        utilisateur.setIdRole(resultSet.getLong("id_role"));
        return utilisateur;
    }
}
