package dto;

import annotation.FieldAnnotation;

public class SignupDto {
    @FieldAnnotation(name = "nom")
    String nom;
    @FieldAnnotation(name = "prenom")
    String prenom;
    @FieldAnnotation(name = "dateNaissance")
    String dateNaissance;
    @FieldAnnotation(name = "email")
    String email;
    @FieldAnnotation(name = "contact")
    String contact;
    @FieldAnnotation(name = "mdp")
    String mdp;
    @FieldAnnotation(name = "idRole")
    String idRole;
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
    public String getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getIdRole() {
        return idRole;
    }
    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }
}
